package com.yoshua.vestiapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoshua.vestiapp.data.local.AppDatabase
import com.yoshua.vestiapp.data.local.EstadoProbador
import com.yoshua.vestiapp.data.local.FilaEsperaEntity
import com.yoshua.vestiapp.data.local.ProbadorEntity
import com.yoshua.vestiapp.data.repository.ProbadorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProbadorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProbadorRepository
    private val _uiState = MutableStateFlow(VestiUiState())
    val uiState: StateFlow<VestiUiState> = _uiState.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = ProbadorRepository(database.probadorDao())

        precargarDatosSiEsNecesario()
        observarDatos()
    }

    private fun precargarDatosSiEsNecesario() {
        viewModelScope.launch {
            repository.inicializarProbadoresSiEstaVacio(
                listOf(
                    ProbadorEntity(numero = 1, estado = EstadoProbador.LIBRE),
                    ProbadorEntity(numero = 2, estado = EstadoProbador.OCUPADO, tiempoUsoMinutos = 12, nombreCliente = "Cliente B", cantidadPrendas = 3),
                    ProbadorEntity(numero = 3, estado = EstadoProbador.TIEMPO_EXCEDIDO, tiempoUsoMinutos = 27, nombreCliente = "Cliente C", cantidadPrendas = 5),
                    ProbadorEntity(numero = 4, estado = EstadoProbador.LIBRE)
                )
            )
        }
    }

    private fun observarDatos() {
        viewModelScope.launch {
            combine(repository.todosLosProbadores, repository.filaEspera) { probadores, fila ->
                val libres = probadores.count { it.estado == EstadoProbador.LIBRE }
                val ocupados = probadores.count { it.estado != EstadoProbador.LIBRE }
                val enEspera = fila.size

                VestiUiState(
                    probadores = probadores,
                    filaEspera = fila,
                    cantidadLibres = libres,
                    cantidadOcupados = ocupados,
                    cantidadEnEspera = enEspera,
                    cargando = false
                )
            }.collect { estadoActualizado ->
                _uiState.value = estadoActualizado
            }
        }
    }

    fun cambiarEstadoProbador(probador: ProbadorEntity, nuevoEstado: EstadoProbador) {
        viewModelScope.launch {
            val actualizado = probador.copy(
                estado = nuevoEstado,
                tiempoUsoMinutos = if (nuevoEstado == EstadoProbador.LIBRE) 0 else probador.tiempoUsoMinutos
            )
            repository.actualizarProbador(actualizado)
        }
    }

    fun agregarClienteAFila(nombre: String, prendas: Int) {
        viewModelScope.launch {
            repository.agregarAFila(
                FilaEsperaEntity(nombreCliente = nombre, cantidadPrendas = prendas, tiempoEsperaMinutos = 5)
            )
        }
    }
}
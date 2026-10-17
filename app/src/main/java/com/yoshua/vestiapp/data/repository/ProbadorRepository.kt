package com.yoshua.vestiapp.data.repository

import com.yoshua.vestiapp.data.local.FilaEsperaEntity
import com.yoshua.vestiapp.data.local.ProbadorDao
import com.yoshua.vestiapp.data.local.ProbadorEntity
import kotlinx.coroutines.flow.Flow

class ProbadorRepository(private val probadorDao: ProbadorDao) {
    val todosLosProbadores: Flow<List<ProbadorEntity>> = probadorDao.ontenerTodosLosProbadores()
    val filaEspera: Flow<List<FilaEsperaEntity>> = probadorDao.obtenerFilaEspera()

    suspend fun inicializarProbadoresSiEstaVacio(probadoresIniciales: List<ProbadorEntity>){
        probadorDao.insertarProbador(probadoresIniciales)
    }
    suspend fun actualizarProbador(probador: ProbadorEntity){
        probadorDao.actualizarProbador(probador)
    }
    suspend fun agregarAFila(cliente: FilaEsperaEntity){
        probadorDao.agregarAFila(cliente)
    }
    suspend fun eliminarDeFila(id: Int){
        probadorDao.eliminarDeFila(id)
    }
}
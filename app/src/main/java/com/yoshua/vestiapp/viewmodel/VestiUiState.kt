package com.yoshua.vestiapp.viewmodel

import com.yoshua.vestiapp.data.local.FilaEsperaEntity
import com.yoshua.vestiapp.data.local.ProbadorEntity


data class VestiUiState(
    val probadores: List<ProbadorEntity> = emptyList(),
    val filaEspera: List<FilaEsperaEntity> = emptyList(),
    val cantidadLibres: Int = 0,
    val cantidadOcupados: Int = 0,
    val cantidadEnEspera: Int = 0,
    val cargando: Boolean = false
)
package com.yoshua.vestiapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class EstadoProbador {
    LIBRE,
    OCUPADO,
    TIEMPO_EXCEDIDO
}

@Entity(tableName = "probadores")
data class ProbadorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val numero: Int,
    val estado: EstadoProbador = EstadoProbador.LIBRE,
    val tiempoUsoMinutos: Int = 0,
    val nombreCliente: String = "",
    val cantidadPrendas: Int = 0
)
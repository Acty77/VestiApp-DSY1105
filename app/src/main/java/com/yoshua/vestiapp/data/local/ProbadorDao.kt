package com.yoshua.vestiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProbadorDao {

    @Query("SELECT * FROM probadores ORDER BY numero ASC")
    fun ontenerTodosLosProbadores(): Flow<List<ProbadorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProbador(probadores: List<ProbadorEntity>)

    @Update
    suspend fun actualizarProbador(probadores: ProbadorEntity)

    //FILA DE ESPERA
    @Query("SELECT * FROM filas_espera ORDER BY id ASC")
    fun obtenerFilaEspera(): Flow<List<FilaEsperaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAFila(cliente: FilaEsperaEntity)

    @Query("DELETE FROM filas_espera WHERE id = :id")
    suspend fun eliminarDeFila(id: Int)
}
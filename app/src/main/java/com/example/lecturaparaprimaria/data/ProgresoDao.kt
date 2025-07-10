package com.example.lecturaparaprimaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgresoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarProgreso(progreso: Progreso)

    @Query("SELECT * FROM progreso WHERE nivel = :nivel ORDER BY timestamp DESC LIMIT 1")
    fun obtenerProgresoPorNivel(nivel: Int): Flow<Progreso?>
}
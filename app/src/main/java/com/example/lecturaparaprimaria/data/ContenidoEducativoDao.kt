package com.example.lecturaparaprimaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContenidoEducativoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContenido(vararg contenido: ContenidoEducativo)

    @Query("SELECT * FROM contenidos WHERE id = :id")
    suspend fun obtenerContenido(id: Int): ContenidoEducativo?
}
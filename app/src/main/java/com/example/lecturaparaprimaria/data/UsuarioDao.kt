package com.example.lecturaparaprimaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertar(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuario>
}

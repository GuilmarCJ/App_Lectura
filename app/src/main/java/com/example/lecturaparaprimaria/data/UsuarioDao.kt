package com.example.lecturaparaprimaria.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario): Long


    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuario>

    // Nuevo método para actualizar el avatar
    @Query("UPDATE usuarios SET avatarId = :avatarId WHERE nombre = :nombre")
    suspend fun actualizarAvatar(nombre: String, avatarId: Int)

    @Query("SELECT * FROM usuarios WHERE nombre = :nombre LIMIT 1")
    suspend fun obtenerPorNombre(nombre: String): Usuario

    @Delete
    suspend fun eliminar(usuario: Usuario)
}

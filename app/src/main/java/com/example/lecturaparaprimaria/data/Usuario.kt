package com.example.lecturaparaprimaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val nombre: String,
    val avatar: String,            // Ruta o nombre del archivo del avatar
    val rango: String = "F",       // Rango inicial
    val nivelesSuperados: Int = 0, // Contador de niveles
    val monedas: Int = 0           // Monedas acumuladas
)

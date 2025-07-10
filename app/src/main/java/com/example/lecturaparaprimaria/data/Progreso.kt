package com.example.lecturaparaprimaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progreso")
data class Progreso(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nivel: Int,
    val correctas: Int,
    val incorrectas: Int,
    val nota: Double,
    val timestamp: Long = System.currentTimeMillis()
)
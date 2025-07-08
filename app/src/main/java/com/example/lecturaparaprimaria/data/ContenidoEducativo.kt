package com.example.lecturaparaprimaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contenidos")
data class ContenidoEducativo(
    @PrimaryKey val id: Int,          // ID del nivel (1, 2, 3...)
    val titulo: String,               // Ej: "Pedro y Pablo"
    val texto: String,                // El cuento completo
    val preguntas: List<Pregunta>     // Lista de preguntas
)

@Entity(tableName = "preguntas")
data class Pregunta(
    @PrimaryKey val id: Int,
    val contenidoId: Int,             // ID del contenido al que pertenece
    val texto: String,                // Enunciado de la pregunta
    val opciones: List<String>,       // Opciones de respuesta
    val respuestaCorrecta: Int        // Índice de la opción correcta (ej: 0 = A)
)
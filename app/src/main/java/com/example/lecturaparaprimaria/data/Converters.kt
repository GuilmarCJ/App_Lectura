package com.example.lecturaparaprimaria.data

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    // Para List<Pregunta>
    @TypeConverter
    fun fromPreguntasList(preguntas: List<Pregunta>): String {
        return Gson().toJson(preguntas)
    }

    @TypeConverter
    fun toPreguntasList(json: String): List<Pregunta> {
        return Gson().fromJson(json, object : TypeToken<List<Pregunta>>() {}.type)
    }

    // Para List<String>
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
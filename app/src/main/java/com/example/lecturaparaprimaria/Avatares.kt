package com.example.lecturaparaprimaria

object Avatares {
    // Lista de pares (ID, Recurso Drawable)
    val lista = listOf(
        1 to R.drawable.avatar_1,
        2 to R.drawable.avatar_2,
        3 to R.drawable.avatar_3,
        4 to R.drawable.avatar_4,
        5 to R.drawable.avatar_5,
        6 to R.drawable.avatar_6,
        7 to R.drawable.avatar_7,
        8 to R.drawable.avatar_8,
        9 to R.drawable.avatar_9,
        10 to R.drawable.avatar_10,
        11 to R.drawable.avatar_11,
        12 to R.drawable.avatar_12,
        13 to R.drawable.avatar_13,
        14 to R.drawable.avatar_14,
        15 to R.drawable.avatar_15,
        16 to R.drawable.avatar_16,
        17 to R.drawable.avatar_17,
        18 to R.drawable.avatar_18,
        19 to R.drawable.avatar_19
    )

    fun obtenerDrawable(id: Int): Int {
        return lista.find { it.first == id }?.second ?: R.drawable.avatar_1
    }

    // Función para obtener el ID lógico del recurso drawable
    fun obtenerIdLogico(drawableId: Int): Int {
        return lista.find { it.second == drawableId }?.first ?: 1
    }
}
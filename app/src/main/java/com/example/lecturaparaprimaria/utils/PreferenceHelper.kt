package com.example.lecturaparaprimaria.utils


import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "lectura_pref"
    private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isOnboardingShown(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_ONBOARDING_SHOWN, false)
    }

    fun setOnboardingShown(context: Context) {
        getPrefs(context).edit().putBoolean(KEY_ONBOARDING_SHOWN, true).apply()
    }
    fun setUsuarioActual(context: Context, nombre: String) {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("usuario_actual", nombre).apply()
    }

    fun getUsuarioActual(context: Context): String? {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        return prefs.getString("usuario_actual", null)
    }

}
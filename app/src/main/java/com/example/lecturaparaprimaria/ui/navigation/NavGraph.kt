package com.example.lecturaparaprimaria.ui.navigation



import androidx.compose.foundation.layout.Box

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.ui.screens.OnboardingScreen
import com.example.lecturaparaprimaria.ui.screens.PantallaPrincipal
import com.example.lecturaparaprimaria.ui.screens.PantallaRegistro
import com.example.lecturaparaprimaria.ui.screens.PantallaSeleccionAvatar
import com.example.lecturaparaprimaria.ui.screens.PantallaSeleccionUsuario

import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.launch


@Composable
fun NavGraph(
    database: AppDatabase,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    var pantallaActual by remember { mutableStateOf("onboarding") }
    var usuarioActivo by remember { mutableStateOf<Usuario?>(null) }

    suspend fun refrescarUsuario(nombre: String) {
        usuarioActivo = database.usuarioDao().obtenerPorNombre(nombre)
    }

    Box(modifier) {
        when (pantallaActual) {

            "onboarding" -> OnboardingScreen(
                onStartClicked = { pantallaActual = "registro" }
            )

            "registro" -> PantallaRegistro(
                database = database,
                onUsuarioRegistrado = { usuario ->
                    usuarioActivo = usuario
                    pantallaActual = "seleccionar_avatar"
                },
                irAPantallaUsuarios = { pantallaActual = "seleccionar" }
            )

            "seleccionar" -> PantallaSeleccionUsuario(
                database = database,
                onUsuarioSeleccionado = { usuario ->
                    usuarioActivo = usuario
                    pantallaActual = if (usuario.avatarId == -1) "seleccionar_avatar" else "principal"
                },
                onBack = { pantallaActual = "registro" }
            )

            "seleccionar_avatar" -> PantallaSeleccionAvatar(
                usuario = usuarioActivo!!,
                database = database,
                onAvatarSeleccionado = {
                    coroutineScope.launch {
                        refrescarUsuario(usuarioActivo!!.nombre)
                        pantallaActual = "principal"
                    }
                }
            )

            "principal" -> {
                LaunchedEffect(Unit) {
                    if (usuarioActivo != null) {
                        refrescarUsuario(usuarioActivo!!.nombre)
                    }
                }
                PantallaPrincipal(usuario = usuarioActivo!!)
            }


        }
    }
}
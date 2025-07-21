package com.example.lecturaparaprimaria.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState

import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.ContenidoEducativo
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.ui.screens.Nivel1Screen
import com.example.lecturaparaprimaria.ui.screens.Nivel2Screen
import com.example.lecturaparaprimaria.ui.screens.Nivel3Screen
import com.example.lecturaparaprimaria.ui.screens.OnboardingScreen
import com.example.lecturaparaprimaria.ui.screens.PantallaPrincipal
import com.example.lecturaparaprimaria.ui.screens.PantallaRegistro
import com.example.lecturaparaprimaria.ui.screens.PantallaSeleccionAvatar
import com.example.lecturaparaprimaria.ui.screens.PantallaSeleccionUsuario

import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.launch

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.lecturaparaprimaria.data.SyncRepository
import com.example.lecturaparaprimaria.ui.screens.PantallaInicioSesion
import com.example.lecturaparaprimaria.ui.screens.PantallaCambioCredenciales
import com.example.lecturaparaprimaria.utils.PreferenceHelper

@Composable
fun NavGraph(
    database: AppDatabase,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val syncRepository = remember { SyncRepository(database) }

    var pantallaActual by remember {
        mutableStateOf(
            when {
                !PreferenceHelper.isOnboardingShown(context) -> "onboarding"
                PreferenceHelper.getUsuarioActual(context) != null -> "principal"
                else -> "inicio_sesion"
            }
        )
    }


    var usuarioActivo by remember { mutableStateOf<Usuario?>(null) }



    suspend fun refrescarUsuario(nombre: String) {
        usuarioActivo = database.usuarioDao().obtenerPorNombre(nombre)
    }

    Box(modifier) {
        when (pantallaActual) {

            "onboarding" -> OnboardingScreen(
                onStartClicked = { pantallaActual = "inicio_sesion" }
            )


            "inicio_sesion" -> PantallaInicioSesion(
                database = database,
                syncRepository = syncRepository, // ← AÑADE ESTO
                onLoginSuccess = { usuario ->
                    usuarioActivo = usuario
                    pantallaActual = "cambio_credenciales"
                },
                onBack = {
                    pantallaActual = "onboarding"
                }
            )

            "cambio_credenciales" -> PantallaCambioCredenciales(
                usuarioActual = usuarioActivo!!.nombre,
                claveActual = "clave", // si guardas la clave, pásala aquí
                syncRepository = syncRepository,
                onCredencialesActualizadas = { nuevoNombre ->
                    coroutineScope.launch {
                        // ✅ Guarda en Room el nuevo usuario
                        val nuevoUsuario = Usuario(nombre = nuevoNombre, avatarId = -1)
                        database.usuarioDao().insertar(nuevoUsuario)

                        usuarioActivo = nuevoUsuario
                        pantallaActual = "seleccionar_avatar"
                    }
                }

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
                syncRepository = syncRepository, // ✅ agrégalo aquí
                onAvatarSeleccionado = {
                    coroutineScope.launch {
                        refrescarUsuario(usuarioActivo!!.nombre)
                        pantallaActual = "principal"
                    }
                }
            )

            "principal" -> {
                LaunchedEffect(Unit) {
                    if (usuarioActivo == null) {
                        PreferenceHelper.getUsuarioActual(context)?.let { nombre ->
                            refrescarUsuario(nombre)
                        }
                    } else {
                        refrescarUsuario(usuarioActivo!!.nombre)
                    }
                }

                usuarioActivo?.let { usuario ->
                    PantallaPrincipal(
                        usuario = usuario,
                        onNivelSeleccionado = { nivel ->
                            pantallaActual = when (nivel) {
                                1 -> "nivel1"
                                else -> "principal"
                            }
                        }
                    )
                } ?: Box(Modifier.fillMaxSize()) {
                    Text("Cargando usuario...", Modifier.align(Alignment.Center))
                }
            }


            "nivel1" -> {
                // Obtén el contenido del Nivel 1 desde Room
                val contenido by produceState<ContenidoEducativo?>(
                    initialValue = null,
                    key1 = Unit,
                    producer = {
                        value = database.contenidoDao().obtenerContenido(1)
                    }
                )

                if (contenido != null) {
                    Nivel1Screen(
                        contenido = contenido!!,
                        onRespuestaSeleccionada = { esCorrecta ->
                            coroutineScope.launch {
                                // Aquí puedes guardar en Firebase si deseas
                            }
                        },
                        onBack = { pantallaActual = "principal" },
                        onAvanzarNivel2 = { pantallaActual = "nivel2" }  // ← AGREGA ESTA LÍNEA
                    )

                }}

            "nivel2" -> {
                val contenido by produceState<ContenidoEducativo?>(
                    initialValue = null,
                    key1 = Unit,
                    producer = {
                        value = database.contenidoDao().obtenerContenido(2)
                    }
                )

                if (contenido != null) {
                    Nivel2Screen(
                        contenido = contenido!!,
                        onRespuestaSeleccionada = { esCorrecta ->
                            coroutineScope.launch {
                                // Guardar progreso en Firebase si deseas
                            }
                        },
                        onBack = { pantallaActual = "principal" },
                        onAvanzarNivel3 = { pantallaActual = "nivel3" } // ← Este será tu siguiente nivel
                    )
                } else {
                    Text("Error: No se encontró el nivel")
                }
            }

            "nivel3" -> {
                val contenido by produceState<ContenidoEducativo?>(
                    initialValue = null,
                    key1 = Unit,
                    producer = {
                        value = database.contenidoDao().obtenerContenido(3)
                    }
                )

                if (contenido != null) {
                    Nivel3Screen(
                        contenido = contenido!!,
                        onRespuestaSeleccionada = { esCorrecta ->
                            coroutineScope.launch {
                                // Guardar en Firebase si deseas
                            }
                        },
                        onBack = { pantallaActual = "principal" },
                        onFinalizar = { pantallaActual = "principal" } // Aquí puedes ir a una pantalla final o resumen
                    )
                } else {
                    Text("Error: No se encontró el nivel 3")
                }
            }



        }
    }
}
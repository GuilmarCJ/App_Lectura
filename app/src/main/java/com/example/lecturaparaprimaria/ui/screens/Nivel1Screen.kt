package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.ContenidoEducativo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nivel1Screen(
    contenido: ContenidoEducativo,
    onRespuestaSeleccionada: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    var preguntaActual by remember { mutableStateOf(0) }
    var respuestaSeleccionada by remember { mutableStateOf<Int?>(null) }
    var correctas by remember { mutableStateOf(0) }
    var incorrectas by remember { mutableStateOf(0) }
    var nivelFinalizado by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(contenido.titulo) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Texto del cuento con formato adecuado
            Text(
                text = contenido.texto,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val pregunta = contenido.preguntas[preguntaActual]

            // Pregunta actual
            Text(
                text = "Pregunta ${preguntaActual + 1}: ${pregunta.texto}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Opciones de respuesta
            pregunta.opciones.forEachIndexed { index, opcion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { respuestaSeleccionada = index },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (respuestaSeleccionada == index)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = opcion,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (respuestaSeleccionada != null) {
                        val esCorrecta = respuestaSeleccionada == pregunta.respuestaCorrecta
                        onRespuestaSeleccionada(esCorrecta)

                        if (esCorrecta) correctas++ else incorrectas++

                        if (preguntaActual < contenido.preguntas.size - 1) {
                            preguntaActual++
                            respuestaSeleccionada = null
                        } else {
                            nivelFinalizado = true // ¡Nivel terminado!
                        }
                    }
                },
                enabled = respuestaSeleccionada != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(if (preguntaActual == contenido.preguntas.lastIndex) "Finalizar" else "Siguiente")
            }

        }
    }
    if (nivelFinalizado) {
        ResultadoNivel(
            correctas = correctas,
            incorrectas = incorrectas,
            onCerrar = {
                nivelFinalizado = false
                preguntaActual = 0
                respuestaSeleccionada = null
                correctas = 0
                incorrectas = 0
            }
        )
    }
}

@Composable
fun ResultadoNivel(
    correctas: Int,
    incorrectas: Int,
    onCerrar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCerrar,
        confirmButton = {
            Button(onClick = onCerrar) {
                Text("Cerrar")
            }
        },
        title = { Text("¡Nivel Completado!") },
        text = {
            Column {
                Text("Respuestas correctas: $correctas")
                Text("Respuestas incorrectas: $incorrectas")
            }
        }
    )
}

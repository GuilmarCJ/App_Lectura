package com.example.lecturaparaprimaria.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.room.Room
import com.example.lecturaparaprimaria.R
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.ContenidoEducativo
import com.example.lecturaparaprimaria.data.Progreso
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nivel1Screen(
    contenido: ContenidoEducativo,
    onRespuestaSeleccionada: (Boolean) -> Unit,
    onBack: () -> Unit,
    onAvanzarNivel2: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var preguntaActual by remember { mutableStateOf(0) }
    var respuestaSeleccionada by remember { mutableStateOf<Int?>(null) }
    var correctas by remember { mutableStateOf(0) }
    var incorrectas by remember { mutableStateOf(0) }
    var nivelFinalizado by remember { mutableStateOf(false) }

    // Guardamos una lista aleatoria de 10 preguntas (esto se mantiene fijo en la recomposición)
    val preguntasSeleccionadas = remember { contenido.preguntas.shuffled().take(10) }


    // Base de datos y progreso anterior
    val db = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "lectura_db").build()
    }
    val progresoAnterior by db.progresoDao().obtenerProgresoPorNivel(1).collectAsState(initial = null)

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
            // Mostrar nota anterior
            progresoAnterior?.let {
                Text(
                    text = "📊 Última nota: ${"%.1f".format(it.nota)} / 20",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Texto principal
            Text(
                text = contenido.texto,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            val pregunta = preguntasSeleccionadas[preguntaActual]

            Text(
                text = "Pregunta ${preguntaActual + 1}: ${pregunta.texto}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

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

                        if (preguntaActual < preguntasSeleccionadas.size - 1) {
                            preguntaActual++
                            respuestaSeleccionada = null
                        } else {
                            nivelFinalizado = true
                            scope.launch {
                                val nota = correctas.toDouble() / preguntasSeleccionadas.size * 20
                                db.progresoDao().guardarProgreso(
                                    Progreso(
                                        nivel = 1,
                                        correctas = correctas,
                                        incorrectas = incorrectas,
                                        nota = nota
                                    )
                                )
                            }

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
                if (correctas > 7) {
                    onAvanzarNivel2() // <- Esto notifica al NavGraph
                } else {
                    onBack()
                }
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
    val context = LocalContext.current

    val imageResId = when (incorrectas) {
        in 8..10 -> R.drawable.img_res1
        in 6..7 -> R.drawable.img_res2
        in 2..5 -> R.drawable.img_res3
        else -> null // ← usamos video si no hay muchas malas
    }

    val soundResId = when (incorrectas) {
        in 8..10 -> R.raw.son_res1
        in 6..7 -> R.raw.son_res2
        in 2..5 -> R.raw.son_res5
        else -> null // usamos video
    }

    // Reproduce sonido si aplica
    LaunchedEffect(Unit) {
        soundResId?.let {
            val mediaPlayer = MediaPlayer.create(context, it)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { it.release() }
        }
    }

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
                Text("✅ Correctas: $correctas")
                Text("❌ Incorrectas: $incorrectas")
                Spacer(modifier = Modifier.height(16.dp))

                if (incorrectas <= 1) {
                    // 🎥 Mostrar video si tiene muy pocas malas
                    AndroidView(
                        factory = {
                            android.widget.VideoView(it).apply {
                                setVideoURI(
                                    android.net.Uri.parse("android.resource://${context.packageName}/${R.raw.video_res4}")
                                )
                                setOnPreparedListener { mp -> mp.isLooping = false }
                                start()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                } else {
                    imageResId?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Resultado",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                    }
                }
            }
        }
    )
}

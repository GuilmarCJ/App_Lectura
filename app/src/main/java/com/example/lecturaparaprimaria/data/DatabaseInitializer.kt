package com.example.lecturaparaprimaria.data

object DatabaseInitializer {
    suspend fun inicializarContenidos(db: AppDatabase) {
        val nivel1 = ContenidoEducativo(
            id = 1,
            titulo = "Pedro y Pablo",
            texto = "Pedro y Pablo eran vecinos y amigos. El primero era pobre, y el segundo, rico. Un día, Pablo le dijo a Pedro:\n" +
                    "—Amigo, tu gallo me está arruinando el jardín. Si no te deshaces de él, le tuerzo el pescuezo.\n" +
                    "\n" +
                    "Pedro cogió al plumífero y se lo obsequió al gobernador, quien aceptó y dio órdenes para que, al mediodía, tuvieran el pollo asado. Pedro participaría en el almuerzo. El gobernador le dijo a Pedro: «¡Repartíelo! Sé equitativo. Si no, serás azotado; si lo haces perfectamente, serás premiado.»\n" +
                    "\n" +
                    "Pedro cortó la cabeza del ave y se la dio al señor, alegando que él era la cabeza de la familia; el cuello se lo dio a la señora, argumentando que este y la cabeza eran una; las alas, a las dos hijas, ya que así sabrían escribir mejor; y las patas, a las dos hijas, para que sean excelentes bailarinas. Finalmente, dijo:\n" +
                    "—Ya tienen todos lo que les pertenece. Yo me conformaba con el resto.\n" +
                    "\n" +
                    "El gobernador premió a Pedro con muchas regalías. Pablo se enteró, cogió los mejores cinco gallos de su granja, les torció el cuello y los llevó al gobernador, quien aceptó con la condición de que vaya a comer para que los distribuya equitativamente. Si no lograba hacerlo, lo castigarían con veinte azotes; si lo hacía, lo premiarían; pero, cegado por la envidia y la ambición, nada se le ocurrió. Llamaron a Pedro.\n" +
                    "\n" +
                    "—Creo —dijo Pedro, distribuyendo las aves— que su señoría y su esposa más un gallo forman una trinidad; las hijas varones y otro gallo forman otra trinidad; las hijas y otro gallo son una trinidad. Y tú, pobre diablo, y los otros dos gallos ¡son otra trinidad!\n" +
                    "\n" +
                    "El gobernador dijo: «Por su buena razón y sentido de justicia le obsequio una casa de campo y tierras.\n" +
                    "A ti, Pablo, por tu falta de juicio e imaginación y por ser tan avaro, que te den veinte latigazos.»\n" +
                    "\n" +
                    "Bibliografía:\n" +
                    "González, M. (2009). Pedro y Pablo. En 20 cuentos escogidos. Madrid: Susaeta. (Adaptación)\n", // Texto completo del cuento
            preguntas = listOf(
                Pregunta(
                    id = 1,
                    contenidoId = 1,
                    texto = "¿Qué tipo de texto es 'Pedro y Pablo'?",
                    opciones = listOf(
                        "A) Un cuento",
                        "B) Una noticia",
                        "C) Una fábula",
                        "D) Un poema"
                    ),
                    respuestaCorrecta = 0
                ),
                Pregunta(
                    id = 2,
                    contenidoId = 1,
                    texto = "¿Quiénes eran vecinos y amigos?",
                    opciones = listOf(
                        "A) Pedro y Juan",
                        "B) Pedro y Pablo",
                        "C) Pablo y Luis",
                        "D) Juan y Pablo"
                    ),
                    respuestaCorrecta = 1
                ),
                Pregunta(
                    id = 3,
                    contenidoId = 1,
                    texto = "¿Cuál de los dos amigos era pobre?",
                    opciones = listOf(
                        "A) Pablo",
                        "B) Ninguno",
                        "C) Pedro",
                        "D) Los dos"
                    ),
                    respuestaCorrecta = 2
                ),
                Pregunta(
                    id = 4,
                    contenidoId = 1,
                    texto = "¿Qué molestaba a Pablo?",
                    opciones = listOf(
                        "A) El perro de Pedro",
                        "B) El ruido",
                        "C) Las hijas de Pedro",
                        "D) El gallo de Pedro"
                    ),
                    respuestaCorrecta = 3
                ),
                Pregunta(
                    id = 5,
                    contenidoId = 1,
                    texto = "¿Qué dijo Pablo sobre el gallo?",
                    opciones = listOf(
                        "A) Que le arruinaba el jardín",
                        "B) Que era bonito",
                        "C) Que cantaba bien",
                        "D) Que era ruidoso"
                    ),
                    respuestaCorrecta = 0
                ),
                Pregunta(
                    id = 6,
                    contenidoId = 1,
                    texto = "¿Qué hizo Pedro con el gallo?",
                    opciones = listOf(
                        "A) Lo vendió",
                        "B) Se lo dio al gobernador",
                        "C) Lo escondió",
                        "D) Lo soltó"
                    ),
                    respuestaCorrecta = 1
                ),
                Pregunta(
                    id = 7,
                    contenidoId = 1,
                    texto = "¿Qué hizo el gobernador con el gallo?",
                    opciones = listOf(
                        "A) Lo regaló",
                        "B) Lo cuidó",
                        "C) Lo mandó asar",
                        "D) Lo soltó"
                    ),
                    respuestaCorrecta = 2
                ),
                Pregunta(
                    id = 8,
                    contenidoId = 1,
                    texto = "¿Qué debía hacer Pedro con el pollo asado?",
                    opciones = listOf(
                        "A) Comérselo solo",
                        "B) Tirarlo",
                        "C) Guardarlo",
                        "D) Repartirlo equitativamente"
                    ),
                    respuestaCorrecta = 3
                ),
                Pregunta(
                    id = 9,
                    contenidoId = 1,
                    texto = "¿Qué pasaría si Pedro repartía mal el pollo?",
                    opciones = listOf(
                        "A) Lo azotarían",
                        "B) Lo premiarían",
                        "C) Lo felicitarían",
                        "D) Lo expulsarían"
                    ),
                    respuestaCorrecta = 0
                ),
                Pregunta(
                    id = 10,
                    contenidoId = 1,
                    texto = "¿A quién le dio Pedro la cabeza del ave?",
                    opciones = listOf(
                        "A) A la esposa",
                        "B) A Pablo",
                        "C) Al gobernador",
                        "D) A la hija"
                    ),
                    respuestaCorrecta = 2
                )
            )
        )

        db.contenidoDao().insertarContenido(nivel1)
    }
}
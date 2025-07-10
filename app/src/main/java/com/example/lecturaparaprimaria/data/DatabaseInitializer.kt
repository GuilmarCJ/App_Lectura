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
                ),
                Pregunta(11, 1, "¿Por qué le dio el cuello a la señora?",
                    listOf("A) Porque estaba unido a la cabeza", "B) Porque era largo", "C) Porque sobraba", "D) Porque se lo pidió"), respuestaCorrecta =0),
                Pregunta(12, 1, "¿Qué partes del ave dio a las hijas?",
                    listOf("A) Cabeza y cuello", "B) Plumas y pico", "C) Alas y patas", "D) Cuerpo y huesos"), respuestaCorrecta =2),
                Pregunta(13, 1, "¿Por qué dio las alas a las hijas?",
                    listOf("A) Para volar", "B) Para leer mejor", "C) Para que naden", "D) Para que escriban"), respuestaCorrecta =3),
                Pregunta(14, 1, "¿Por qué les dio las patas?",
                    listOf("A) Para que bailen", "B) Para que jueguen", "C) Para que naden", "D) Para que salten"), respuestaCorrecta =0),
                Pregunta(15, 1, "¿Qué parte se quedó Pedro?",
                    listOf("A) Cabeza", "B) Alas", "C) El resto del ave", "D) Ninguna"), 2),
                Pregunta(16, 1, "¿Qué premio recibió Pedro?",
                    listOf("A) Muchas regalías", "B) Un aplauso", "C) Un libro", "D) Otro gallo"), 0),
                Pregunta(17, 1, "¿Qué hizo Pablo al enterarse del premio?",
                    listOf("A) Lloró", "B) Se fue del pueblo", "C) Llevó cinco gallos al gobernador", "D) Felicitó a Pedro"), respuestaCorrecta =2),
                Pregunta(18, 1, "¿Cuántos gallos llevó Pablo al gobernador?",
                    listOf("A) Tres", "B) Cuatro", "C) Seis", "D) Cinco"), respuestaCorrecta =3),
                Pregunta(19, 1, "¿Qué debía hacer Pablo con los gallos?",
                    listOf("A) Repartirlos equitativamente", "B) Guardarlos", "C) Venderlos", "D) Cocinarlos"), respuestaCorrecta =0),
                Pregunta(20, 1, "¿Qué le harían a Pablo si no los repartía bien?",
                    listOf("A) Le quitarían los gallos", "B) Le darían veinte azotes", "C) Lo premiarían", "D) Lo felicitarían"), respuestaCorrecta =1),
                Pregunta(21, 1, "¿Qué le ocurrió a Pablo al no saber qué hacer?",
                    listOf("A) Se escapó", "B) Le dieron más gallos", "C) Llamaron a Pedro", "D) Le aplaudieron"), respuestaCorrecta =2),
                Pregunta(22, 1, "¿Qué hizo Pedro al regresar?",
                    listOf("A) Se fue", "B) Se molestó", "C) Guardó silencio", "D) Repartió los gallos"), respuestaCorrecta =3),
                Pregunta(23, 1, "¿Cómo organizó Pedro los grupos?",
                    listOf("A) En trinidad", "B) Por edades", "C) Por tamaños", "D) Al azar"), respuestaCorrecta =0),
                Pregunta(24, 1, "¿Qué dijo Pedro sobre Pablo?",
                    listOf("A) Que era sabio", "B) Que era justo", "C) Que merecía un premio", "D) Que era un pobre diablo"), respuestaCorrecta =3),
                Pregunta(25, 1, "¿Qué premio recibió Pedro al final?",
                    listOf("A) Casa de campo y tierras", "B) Una medalla", "C) Un burro", "D) Un caballo"), respuestaCorrecta =0),
                Pregunta(26, 1, "¿Qué castigo recibió Pablo?",
                    listOf("A) Nada", "B) Veinte latigazos", "C) Irse del pueblo", "D) Un castigo leve"), respuestaCorrecta =1),
                Pregunta(27, 1, "¿Por qué castigaron a Pablo?",
                    listOf("A) Por molestar al gobernador", "B) Por avaricia y envidia", "C) Por romper un gallo", "D) Por llegar tarde"), respuestaCorrecta =1),
                Pregunta(28, 1, "¿Qué valor mostró Pedro?",
                    listOf("A) Codicia", "B) Rabia", "C) Inteligencia y humildad", "D) Impaciencia"), respuestaCorrecta =2),
                Pregunta(29, 1, "¿Qué sentimiento tenía Pablo?",
                    listOf("A) Alegría", "B) Gratitud", "C) Tristeza", "D) Envidia"), respuestaCorrecta =3),
                Pregunta(30, 1, "¿Quién dio los premios y castigos?",
                    listOf("A) El gobernador", "B) Pedro", "C) La señora", "D) Las hijas"), respuestaCorrecta =0),
                Pregunta(31, 1, "¿Qué parte representa la cabeza en la familia?",
                    listOf("A) Las hijas", "B) El señor", "C) El gallo", "D) La señora"), respuestaCorrecta =1),
                Pregunta(32, 1, "¿Qué representa el cuello según Pedro?",
                    listOf("A) Que está unido a la cabeza", "B) Que es fuerte", "C) Que es largo", "D) Que está separado"), respuestaCorrecta =0),
                Pregunta(33, 1, "¿Cómo era Pedro?",
                    listOf("A) Rencoroso", "B) Pobre pero sabio", "C) Inteligente y justo", "D) Mentiroso"), respuestaCorrecta =2),
                Pregunta(34, 1, "¿Qué aprendemos de Pedro?",
                    listOf("A) Que hay que ser justo", "B) Que es mejor ser rico", "C) Que no hay que compartir", "D) Que hay que envidiar"), respuestaCorrecta =0),
                Pregunta(35, 1, "¿Qué nos enseña el cuento?",
                    listOf("A) Que debemos castigar", "B) Que hay que ser ricos", "C) Que ser justo trae recompensa", "D) Que es mejor no compartir"), respuestaCorrecta =2),
                Pregunta(36, 1, "¿Cómo actuó Pablo al ver el éxito de Pedro?",
                    listOf("A) Con gratitud", "B) Con envidia", "C) Con tristeza", "D) Con alegría"), respuestaCorrecta =1),
                Pregunta(37, 1, "¿Por qué Pedro repartió bien el ave?",
                    listOf("A) Por casualidad", "B) Por ayuda del gobernador", "C) Porque pensó con lógica", "D) Por miedo"), respuestaCorrecta =2),
                Pregunta(38, 1, "¿Qué parte del cuento muestra un conflicto?",
                    listOf("A) Cuando Pedro cocina", "B) Cuando Pablo se queja del gallo", "C) Cuando el gobernador habla", "D) Cuando comen el pollo"), respuestaCorrecta =1),
                Pregunta(39, 1, "¿Cómo termina el cuento?",
                    listOf("A) Pedro premiado y Pablo castigado", "B) Todos son amigos", "C) Todos son felices", "D) El gallo revive"), respuestaCorrecta =0),
                Pregunta(40, 1, "¿Qué valores se destacan en Pedro?",
                    listOf("A) Pereza", "B) Egoísmo", "C) Inteligencia y humildad", "D) Envidia"), respuestaCorrecta =2),
                Pregunta(41, 1, "¿Quién fue avaro y envidioso?",
                    listOf("A) Pedro", "B) Pablo", "C) El gobernador", "D) Nadie"), respuestaCorrecta =1),
                Pregunta(42, 1, "¿Qué tipo de persona era Pablo?",
                    listOf("A) Codiciosa", "B) Honesta", "C) Amable", "D) Justa"), respuestaCorrecta =0),
                Pregunta(43, 1, "¿Qué recurso usó Pedro para resolver el problema?",
                    listOf("A) Magia", "B) Trucos", "C) Inteligencia", "D) Amigos"), respuestaCorrecta =2),
                Pregunta(44, 1, "¿Qué animales son importantes en el cuento?",
                    listOf("A) Gallos", "B) Perros", "C) Gatos", "D) Vacas"), respuestaCorrecta =0),
                Pregunta(45, 1, "¿Qué hizo el gobernador con justicia?",
                    listOf("A) Dio todo a Pablo", "B) Premió a Pedro y castigó a Pablo", "C) Ignoró a Pedro", "D) Regaló los gallos"), respuestaCorrecta =1),
                Pregunta(46, 1, "¿Qué parte del texto es divertida?",
                    listOf("A) Cuando Pablo se enoja", "B) Cuando Pedro reparte el ave", "C) Cuando cae lluvia", "D) Cuando llega un caballo"), respuestaCorrecta =1),
                Pregunta(47, 1, "¿Qué hizo que Pedro gane la confianza del gobernador?",
                    listOf("A) Cocinar", "B) Ser justo al repartir", "C) Ser fuerte", "D) Ser amigo de Pablo"), respuestaCorrecta =1),
                Pregunta(48, 1, "¿Qué aprendemos de la actitud de Pablo?",
                    listOf("A) Que hay que copiar", "B) Que la envidia ayuda", "C) Que la envidia no sirve", "D) Que es mejor mentir"), respuestaCorrecta =2),
                Pregunta(49, 1, "¿Qué nos enseña el final del cuento?",
                    listOf("A) Que todos ganan", "B) Que hay que castigar", "C) Que es mejor ser malo", "D) Que la justicia premia"), respuestaCorrecta =3),
                Pregunta(50, 1, "¿Cuál es el título del texto?",
                    listOf("A) Pedro y Pablo", "B) El gallo generoso", "C) Pedro y el gallo", "D) La venganza de Pablo"), respuestaCorrecta =0),

                )
        )

        db.contenidoDao().insertarContenido(nivel1)
    }
}
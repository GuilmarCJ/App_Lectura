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

        val nivel2 = ContenidoEducativo(
            id = 2,
            titulo = "El Pájaro Misterioso",
            texto = "Dos pájaros descansaban en la rama de un árbol, de pronto echaron a volar y el árbol se quiso marchitar de tristeza.\n\n" +
                    "A los pocos días llegó un pájaro con pico de oro y alas de fuego que se hacía llamar El Pájaro Misterioso.\n\n" +
                    "—¿Por qué tan triste, árbol? —le preguntó El Pájaro Misterioso.\n\n" +
                    "El árbol le contó que estaba muy fastidiado porque las hormigas le picaban todo el día y que no venían pájaros a despertarlo por las mañanas.\n\n" +
                    "El Pájaro Misterioso levantó el pico y se puso a cantar. A los pocos minutos, llegaron los dos pájaros que se habían ido, seguidos de muchos otros pájaros de distintos colores y empezaron a dar vueltas alrededor del árbol.\n\n" +
                    "A la mañana siguiente, el árbol se despertó contento y muy temprano, pues El Pájaro Misterioso cantaba sin parar y los otros pájaros lo acompañaban.",
            preguntas = listOf(
                Pregunta(1, 2, "¿Qué tipo de texto es “El Pájaro Misterioso”?", listOf("A) Un cuento", "B) Una receta", "C) Una noticia", "D) Un poema"), respuestaCorrecta =0),
                Pregunta(2, 2, "¿Dónde descansaban los dos pájaros al inicio del cuento?", listOf("A) En el suelo", "B) En una rama", "C) En una jaula", "D) En un nido"), respuestaCorrecta =1),
                Pregunta(3, 2, "¿Qué hicieron los dos pájaros?", listOf("A) Se escondieron", "B) Se durmieron", "C) Echaron a volar", "D) Se pelearon"), respuestaCorrecta =2),
                Pregunta(4, 2, "¿Cómo se sintió el árbol cuando los pájaros se fueron?", listOf("A) Alegre", "B) Aburrido", "C) Confundido", "D) Triste"), respuestaCorrecta =3),
                Pregunta(5, 2, "¿Qué parte del cuerpo tenía de oro el Pájaro Misterioso?", listOf("A) El pico", "B) Las patas", "C) La cola", "D) Las plumas"), respuestaCorrecta =0),
                Pregunta(6, 2, "¿Cómo eran las alas del Pájaro Misterioso?", listOf("A) De hielo", "B) De hojas", "C) De fuego", "D) De plata"), 2),
                Pregunta(7, 2, "¿Cómo se llamaba el nuevo pájaro que llegó?", listOf("A) El Pájaro Alegre", "B) El Gran Pájaro", "C) El Pájaro Colorido", "D) El Pájaro Misterioso"), respuestaCorrecta =3),
                Pregunta(8, 2, "¿Qué hizo el Pájaro Misterioso al llegar?", listOf("A) Le preguntó al árbol por qué estaba triste", "B) Se fue volando", "C) Se durmió", "D) Se escondió"), respuestaCorrecta =0),
                Pregunta(9, 2, "¿Qué le pasaba al árbol?", listOf("A) Tenía frío", "B) Estaba triste y lo picaban las hormigas", "C) No crecía", "D) Se estaba cayendo"), respuestaCorrecta =1),
                Pregunta(10, 2, "¿Qué hacían las hormigas con el árbol?", listOf("A) Lo regaban", "B) Lo picaban", "C) Lo abrazaban", "D) Lo alimentaban"), respuestaCorrecta =1),
                Pregunta(11, 2, "¿Por qué el árbol extrañaba a los pájaros?", listOf("B) Porque lo despertaban cantando", "A) Porque lo cuidaban", "C) Porque le traían frutas", "D) Porque le daban sombra"), respuestaCorrecta = 0),
                Pregunta(12, 2, "¿Qué hizo el Pájaro Misterioso para ayudar?", listOf("C) Se puso a cantar", "A) Se fue", "B) Llamó al guardabosques", "D) Gritó muy fuerte"), respuestaCorrecta = 0),
                Pregunta(13, 2, "¿Qué ocurrió mientras el Pájaro Misterioso cantaba?", listOf("D) Llegaron muchos pájaros", "A) Llovió", "B) Se callaron todos", "C) Las hormigas se fueron"), respuestaCorrecta = 0),
                Pregunta(14, 2, "¿Quiénes volvieron al árbol?", listOf("B) Los dos pájaros que se habían ido", "A) El viento", "C) Los niños", "D) Las ardillas"), respuestaCorrecta = 0),
                Pregunta(15, 2, "¿Cómo eran los nuevos pájaros que llegaron?", listOf("C) De muchos colores", "A) De un solo color", "B) Negros y blancos", "D) Grises"), respuestaCorrecta = 0),
                Pregunta(16, 2, "¿Qué hacían los pájaros alrededor del árbol?", listOf("D) Daban vueltas cantando", "A) Se peleaban", "B) Dormían", "C) Picaban frutas"), respuestaCorrecta = 0),
                Pregunta(17, 2, "¿Cómo se sintió el árbol al día siguiente?", listOf("A) Feliz y contento", "B) Asustado", "C) Enfermo", "D) Cansado"), respuestaCorrecta = 0),
                Pregunta(18, 2, "¿Qué hacía el Pájaro Misterioso al amanecer?", listOf("C) Cantaba sin parar", "A) Dormía", "B) Volaba lejos", "D) Jugaba"), respuestaCorrecta = 0),
                Pregunta(19, 2, "¿Quién acompañaba al Pájaro Misterioso?", listOf("C) Otros pájaros", "A) Nadie", "B) Los ratones", "D) Las hormigas"), respuestaCorrecta = 0),
                Pregunta(20, 2, "¿Qué emoción expresa el final del cuento?", listOf("A) Alegría", "B) Enfado", "C) Tristeza", "D) Miedo"), respuestaCorrecta = 0),
                Pregunta(21, 2, "¿Qué valor muestra el Pájaro Misterioso?", listOf("B) Amistad", "A) Envidia", "C) Codicia", "D) Pereza"), respuestaCorrecta = 0),
                Pregunta(22, 2, "¿Qué aprendemos del cuento?", listOf("A) A ayudar a los demás", "B) A dormir más", "C) A ignorar los problemas", "D) A reírnos del árbol"), respuestaCorrecta = 0),
                Pregunta(23, 2, "¿Por qué el árbol se sentía solo?", listOf("C) Porque no tenía pájaros", "A) Porque no tenía frutas", "B) Porque no lo regaban", "D) Porque era pequeño"), respuestaCorrecta = 0),
                Pregunta(24, 2, "¿Qué hacen los amigos verdaderos?", listOf("C) Ayudan cuando alguien está triste", "A) Se burlan", "B) Se alejan", "D) Se esconden"), respuestaCorrecta = 0),
                Pregunta(25, 2, "¿Qué podemos hacer como el Pájaro Misterioso?", listOf("D) Hacer felices a nuestros amigos", "A) Dormir todo el día", "B) Ignorar a los demás", "C) Volar alto"), respuestaCorrecta = 0),
                Pregunta(26, 2, "¿Cuál es un buen título para este cuento?", listOf("B) El Pájaro Misterioso", "A) El árbol triste", "C) Los colores del bosque", "D) Las hormigas del árbol"), respuestaCorrecta = 0),
                Pregunta(27, 2, "¿Qué hizo que regresaran los pájaros?", listOf("B) El canto del Pájaro Misterioso", "A) El viento", "C) La lluvia", "D) Las frutas"), respuestaCorrecta = 0),
                Pregunta(28, 2, "¿Qué sentía el árbol por las hormigas?", listOf("C) Fastidio", "A) Gratitud", "B) Amor", "D) Felicidad"), respuestaCorrecta = 0),
                Pregunta(29, 2, "¿Qué parte del cuento muestra el problema?", listOf("B) Cuando el árbol está solo y triste", "A) Cuando llegan los pájaros", "C) Cuando cantan", "D) Cuando vuelan"), respuestaCorrecta = 0),
                Pregunta(30, 2, "¿Qué parte del cuento es la solución?", listOf("C) Cuando cantan y todos regresan", "A) Cuando se van los pájaros", "B) Cuando llegan las hormigas", "D) Cuando el árbol duerme"), respuestaCorrecta = 0),
                Pregunta(31, 2, "¿Qué enseñanza nos deja el cuento?", listOf("B) Que debemos ayudar a los tristes", "A) Que es bueno estar solo", "C) Que los pájaros deben volar lejos", "D) Que las hormigas cantan"), respuestaCorrecta = 0),
                Pregunta(32, 2, "¿Qué es lo más especial del Pájaro Misterioso?", listOf("C) Su canto y sus alas de fuego", "A) Sus patas", "B) Su cola", "D) Su tamaño"), respuestaCorrecta = 0),
                Pregunta(33, 2, "¿Cómo es el ambiente del cuento?", listOf("A) Un bosque tranquilo", "B) Una ciudad", "C) Una playa", "D) Una casa"), respuestaCorrecta = 0),
                Pregunta(34, 2, "¿Qué animal NO aparece en el cuento?", listOf("C) Gatos", "A) Hormigas", "B) Pájaros", "D) Árboles"), respuestaCorrecta = 0),
                Pregunta(35, 2, "¿Qué causó la tristeza del árbol?", listOf("B) Que se fue la compañía de los pájaros", "A) Que no tenía hojas", "C) Que no dormía", "D) Que llovía mucho"), respuestaCorrecta = 0),
                Pregunta(36, 2, "¿Qué lo hizo volver a estar feliz?", listOf("B) La llegada de los pájaros", "A) Comer frutas", "C) Las hormigas", "D) El viento"), respuestaCorrecta = 0),
                Pregunta(37, 2, "¿Qué tenían de especial los pájaros nuevos?", listOf("B) Eran de muchos colores", "A) Eran silenciosos", "C) Eran grandes", "D) Eran verdes"), respuestaCorrecta = 0),
                Pregunta(38, 2, "¿Qué mensaje da el cuento sobre la tristeza?", listOf("C) Que se puede superar con ayuda", "A) Que hay que ignorarla", "B) Que dura para siempre", "D) Que es divertida"), respuestaCorrecta = 0),
                Pregunta(39, 2, "¿Qué hizo que todos los pájaros se juntaran?", listOf("B) El canto del Pájaro Misterioso", "A) Una fiesta", "C) El árbol los llamó", "D) Las frutas del árbol"), respuestaCorrecta = 0),
                Pregunta(40, 2, "¿Cuál de estos personajes aparece en el cuento?", listOf("B) Un árbol", "A) Un león", "C) Un niño", "D) Un pez"), respuestaCorrecta = 0),
                Pregunta(41, 2, "¿Qué muestra el cuento sobre los amigos?", listOf("B) Que pueden regresar y ayudar", "A) Que se van para siempre", "C) Que pelean mucho", "D) Que viven lejos"), respuestaCorrecta = 0),
                Pregunta(42, 2, "¿Qué tipo de final tiene el cuento?", listOf("B) Feliz", "A) Triste", "C) Incompleto", "D) De suspenso"), respuestaCorrecta = 0),
                Pregunta(43, 2, "¿Qué hizo el canto del Pájaro Misterioso?", listOf("B) Atrajo a otros pájaros", "A) Asustó a todos", "C) Hizo llover", "D) Espantó al árbol"), respuestaCorrecta = 0),
                Pregunta(44, 2, "¿Qué significa tener alas de fuego en el cuento?", listOf("B) Que es especial y mágico", "A) Que se quema", "C) Que da miedo", "D) Que vuela lento"), respuestaCorrecta = 0),
                Pregunta(45, 2, "¿Qué podemos hacer cuando vemos a alguien triste?", listOf("B) Animarlo como el Pájaro Misterioso", "A) Reírnos", "C) Ignorarlo", "D) Irnos"), respuestaCorrecta = 0),
                Pregunta(46, 2, "¿Cuál fue el cambio principal en el árbol?", listOf("B) Pasó de estar triste a estar feliz", "A) Se enfermó", "C) Se cayó", "D) Se volvió pequeño"), respuestaCorrecta = 0),
                Pregunta(47, 2, "¿Cómo ayudó el canto a mejorar el ambiente?", listOf("B) Con alegría y compañía", "A) Con calor", "C) Con silencio", "D) Con más hormigas"), respuestaCorrecta = 0),
                Pregunta(48, 2, "¿Qué puede representar el Pájaro Misterioso en la vida real?", listOf("B) Una persona que ayuda a otros", "A) Un juguete", "C) Una nube", "D) Una flor"), respuestaCorrecta = 0),
                Pregunta(49, 2, "¿Qué hizo el cuento más interesante?", listOf("B) La llegada del Pájaro Misterioso", "A) Las hormigas", "C) La tristeza", "D) Las ramas"), respuestaCorrecta = 0),
                Pregunta(50, 2, "¿Qué aprendiste del cuento?", listOf("C) Que ayudar puede cambiar la vida de otros", "A) Que es mejor estar solo", "B) Que las hormigas son amigas", "D) Que los árboles vuelan"), respuestaCorrecta = 0)

            )
        )

        val nivel3 = ContenidoEducativo(
            id = 3,
            titulo = "Mirando por la ventana",
            texto = """
        Había una vez un niño que cayó muy enfermo. Tenía que estar todo el día en cama sin poder moverse. Sufría mucho mirando el cielo a través de la ventana. Pasó algún tiempo, hasta que un día vio una extraña sombra en la ventana: era un pingüino que entró a la habitación, le dio las buenas tardes y se fue. Al principio se preguntaba qué sería aquello, pero luego, mientras seguían apareciendo personajes locos, ya no podía dejar de reír.

        En poco tiempo mejoró y pudo volver al colegio y contar lo sucedido. En la mochila de su mejor amigo, vio algo extraño y le preguntó qué era. Finalmente, pudo ver: ¡allí estaban todos los disfraces que había utilizado su buen amigo para intentar alegrarle! Y, desde entonces, nuestro niño nunca deja que nadie esté solo y sin sonreír un rato.
    """.trimIndent(),
            preguntas = listOf(
                Pregunta(1, 3, "¿Qué tipo de texto es “Mirando por la ventana”?", listOf("A) Un cuento", "B) Una carta", "C) Una noticia", "D) Un poema"), respuestaCorrecta =0),
                Pregunta(2, 3, "¿Qué le pasó al niño del cuento?", listOf("A) Se perdió", "B) Se enfermó", "C) Se cayó", "D) Se fue de viaje"), respuestaCorrecta =1),
                Pregunta(3, 3, "¿Dónde pasaba el niño la mayor parte del tiempo?", listOf("A) En el parque", "B) En el colegio", "C) En su cama", "D) En la cocina"), respuestaCorrecta =2),
                Pregunta(4, 3, "¿Qué hacía el niño al mirar por la ventana?", listOf("A) Jugaba", "B) Sufría", "C) Gritaba", "D) Reía"), respuestaCorrecta =1),
                Pregunta(5, 3, "¿Qué vio un día en la ventana?", listOf("A) Una nube", "B) Un avión", "C) Una sombra", "D) Una flor"), respuestaCorrecta =2),
                Pregunta(6, 3, "¿Qué personaje apareció primero?", listOf("A) Un payaso", "B) Un pingüino", "C) Un oso", "D) Un gato"), respuestaCorrecta =1),
                Pregunta(7, 3, "¿Qué hizo el pingüino?", listOf("A) Bailó", "B) Saludó y se fue", "C) Gritó", "D) Le regaló un juguete"), respuestaCorrecta =1),
                Pregunta(8, 3, "¿Qué siguió pasando después del pingüino?", listOf("A) Aparecieron personajes locos", "B) Llegó el médico", "C) Se rompió la ventana", "D) Salió el Sol"), 0),
                Pregunta(9, 3, "¿Cómo reaccionó el niño con los personajes?", listOf("A) Se asustó", "B) Se puso triste", "C) Comenzó a reír", "D) Lloró"), respuestaCorrecta =2),
                Pregunta(10, 3, "¿Qué efecto tuvo la risa en el niño?", listOf("A) Mejoró", "B) Se enfermó más", "C) Se aburrió", "D) Se desmayó"), respuestaCorrecta =0),
                Pregunta(11, 3, "¿A dónde pudo regresar el niño después?", listOf("A) A su casa", "B) Al colegio", "C) Al hospital", "D) A la playa"), respuestaCorrecta =1),
                Pregunta(12, 3, "¿Qué encontró en la mochila de su amigo?", listOf("A) Una carta", "B) Algo extraño", "C) Una merienda", "D) Un libro"), respuestaCorrecta =1),
                Pregunta(13, 3, "¿Qué tenía su mejor amigo en la mochila?", listOf("A) Disfraces", "B) Medicinas", "C) Juguetes", "D) Animales"), respuestaCorrecta =0),
                Pregunta(14, 3, "¿Para qué usó los disfraces el amigo?", listOf("A) Para ir al carnaval", "B) Para alegrar al niño", "C) Para hacer bromas", "D) Para hacer tareas"), respuestaCorrecta =1),
                Pregunta(15, 3, "¿Cómo se sintió el niño al descubrir la verdad?", listOf("A) Decepcionado", "B) Agradecido", "C) Triste", "D) Enfadado"), respuestaCorrecta =1),
                Pregunta(16, 3, "¿Qué aprendió el niño luego de su experiencia?", listOf("A) A no dejar que nadie esté solo", "B) A usar disfraces", "C) A leer cuentos", "D) A tener miedo"),respuestaCorrecta =0),
                Pregunta(17, 3, "¿Cuál es el mensaje del cuento?", listOf("A) Reír es malo", "B) La amistad puede curar", "C) La enfermedad es divertida", "D) Las ventanas son mágicas"), respuestaCorrecta =1),
                Pregunta(18, 3, "¿Cómo era el mejor amigo del niño?", listOf("A) Perezoso", "B) Tímido", "C) Generoso y creativo", "D) Molesto"), respuestaCorrecta =2),
                Pregunta(19, 3, "¿Qué valor muestra el cuento?", listOf("A) Amistad", "B) Envidia", "C) Egoísmo", "D) Rabia"), respuestaCorrecta =0),
                Pregunta(20, 3, "¿Qué causó la recuperación del niño?", listOf("A) La medicina", "B) El tiempo", "C) La risa y el cariño de su amigo", "D) Dormir más"), 2),
                Pregunta(21, 3, "¿Qué aprendemos del mejor amigo?", listOf("A) Que le gusta la magia", "B) Que hizo todo por ver feliz al niño", "C) Que era muy estudioso", "D) Que se burlaba del enfermo"), respuestaCorrecta =1),
                Pregunta(22, 3, "¿Por qué se disfrazaba el amigo?", listOf("A) Para jugar con su hermano", "B) Para alegrar al niño enfermo", "C) Para ir al circo", "D) Para engañar a sus padres"), respuestaCorrecta =1),
                Pregunta(23, 3, "¿Qué emoción se repite en el cuento?", listOf("A) Miedo", "B) Alegría", "C) Enfado", "D) Sorpresa"), respuestaCorrecta =1),
                Pregunta(24, 3, "¿Cómo ayudó la imaginación en el cuento?", listOf("A) Para jugar", "B) Para curar al niño con alegría", "C) Para dormir", "D) Para hacer tareas"), respuestaCorrecta =1),
                Pregunta(25, 3, "¿Qué hizo el niño al volver al colegio?", listOf("A) Estudió", "B) Contó lo sucedido", "C) Se peleó", "D) Lloró"), respuestaCorrecta =1),
                Pregunta(26, 3, "¿Qué significa “no dejar que nadie esté solo”?", listOf("A) Molestar a otros", "B) Acompañar a quienes lo necesitan", "C) Reírse de todos", "D) Cerrar la puerta"), respuestaCorrecta =1),
                Pregunta(27, 3, "¿Cómo fue la transformación del niño?", listOf("A) De alegre a triste", "B) De enfermo a feliz", "C) De ruidoso a callado", "D) De valiente a cobarde"), respuestaCorrecta =1),
                Pregunta(28, 3, "¿Qué parte del cuento muestra la sorpresa?", listOf("A) Cuando enferma", "B) Cuando se cae", "C) Cuando aparece el pingüino", "D) Cuando duerme"), respuestaCorrecta =2),
                Pregunta(29, 3, "¿Por qué los personajes eran \"locos\"?", listOf("A) Porque hablaban raro", "B) Porque eran graciosos y divertidos", "C) Porque corrían", "D) Porque gritaban"), respuestaCorrecta =1),
                Pregunta(30, 3, "¿Cómo actuó el niño al principio con la sombra?", listOf("A) Se rió", "B) Se preguntaba qué era", "C) Se durmió", "D) Gritó"), respuestaCorrecta =1),
                Pregunta(31, 3, "¿Qué parte del cuento fue mágica para el niño?", listOf("A) Dormir", "B) Comer", "C) Ver al pingüino", "D) Llorar"), respuestaCorrecta =2),
                Pregunta(32, 3, "¿Qué sintió el niño al ver más personajes?", listOf("A) Miedo", "B) Aburrimiento", "C) Risa y alegría", "D) Enojo"), respuestaCorrecta =2),
                Pregunta(33, 3, "¿Cómo ayudó el amigo al niño sin que él supiera?", listOf("A) Disfrazándose y animándolo", "B) Llevándole comida", "C) Contándole chismes", "D) Regañándolo"), respuestaCorrecta =0),
                Pregunta(34, 3, "¿Qué significa “no dejar sin sonreír un rato”?", listOf("A) Gritar", "B) Jugar", "C) Hacer reír a los demás", "D) Dormir más"), respuestaCorrecta =2),
                Pregunta(35, 3, "¿Cuál fue el mejor regalo que recibió el niño?", listOf("A) Un celular", "B) La alegría de su amigo", "C) Dinero", "D) Un videojuego"), respuestaCorrecta =1),
                Pregunta(36, 3, "¿Qué hizo que el niño se sienta acompañado?", listOf("A) La visita de personajes", "B) La televisión", "C) El doctor", "D) El celular"), respuestaCorrecta =0),
                Pregunta(37, 3, "¿Qué podemos aprender del amigo?", listOf("A) Que ser buen amigo es ayudar con amor", "B) Que hay que disfrazarse", "C) Que estudiar no es importante", "D) Que hay que enfermarse"), respuestaCorrecta =0),
                Pregunta(38, 3, "¿Qué parte del cuerpo usó el amigo para ayudar?", listOf("A) La cabeza", "B) El corazón y la creatividad", "C) Los pies", "D) Las manos"), respuestaCorrecta =1),
                Pregunta(39, 3, "¿Qué lección deja el cuento a los lectores?", listOf("A) No enfermarse", "B) Alegrar a quienes lo necesitan", "C) Comer bien", "D) Hacer tareas"), respuestaCorrecta =1),
                Pregunta(40, 3, "¿Cómo puede ayudar un niño a un amigo triste?", listOf("A) Ignorándolo", "B) Haciendo algo divertido", "C) Burlándose", "D) No hablándole"), respuestaCorrecta =1),
                Pregunta(41, 3, "¿Qué valor demostró el amigo con sus acciones?", listOf("A) Vanidad", "B) Orgullo", "C) Generosidad", "D) Desconfianza"), respuestaCorrecta =2),
                Pregunta(42, 3, "¿Qué recurso literario aparece con los personajes extraños?", listOf("A) La realidad", "B) La fantasía", "C) La ciencia", "D) El miedo"), respuestaCorrecta =1),
                Pregunta(43, 3, "¿Qué podría haber pasado si el amigo no ayudaba?", listOf("A) Nada", "B) El niño podría no haberse recuperado tan rápido", "C) El niño se iría del país", "D) El pingüino se quedaría"), respuestaCorrecta =1),
                Pregunta(44, 3, "¿Qué aprendemos sobre el poder de la risa?", listOf("A) Que no ayuda", "B) Que puede sanar y dar alegría", "C) Que es peligrosa", "D) Que es mala"), respuestaCorrecta =1),
                Pregunta(45, 3, "¿Qué parte del cuento es más divertida?", listOf("A) Cuando enferma", "B) Cuando se despide", "C) Cuando aparecen los personajes disfrazados", "D) Cuando duerme"), respuestaCorrecta =2),
                Pregunta(46, 3, "¿Qué hace un amigo de verdad?", listOf("A) Se burla", "B) Ignora", "C) Acompaña y se preocupa", "D) Miente"), respuestaCorrecta =2),
                Pregunta(47, 3, "¿Qué hizo el niño al final con lo que aprendió?", listOf("A) Lo olvidó", "B) Se lo guardó", "C) Empezó a ayudar a otros", "D) Lo escribió"), respuestaCorrecta =2),
                Pregunta(48, 3, "¿Qué partes del cuento muestran ternura?", listOf("A) Ninguna", "B) Las acciones del amigo", "C) El silencio del niño", "D) Las hormigas"), respuestaCorrecta =1),
                Pregunta(49, 3, "¿Qué quiere decir “ya no podía dejar de reír”?", listOf("A) Que estaba confundido", "B) Que se lastimó", "C) Que se puso muy feliz", "D) Que gritó"), respuestaCorrecta =2),
                Pregunta(50, 3, "¿Cómo se llama el cuento?", listOf("A) Mirando por la ventana", "B) El niño enfermo", "C) El pingüino mágico", "D) La mochila secreta"), respuestaCorrecta =0)
            )
        )


        db.contenidoDao().insertarContenido(nivel1)

        db.contenidoDao().insertarContenido(nivel2)

    }
}
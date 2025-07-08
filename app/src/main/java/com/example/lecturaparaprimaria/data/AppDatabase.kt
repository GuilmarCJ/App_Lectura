package com.example.lecturaparaprimaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Usuario::class, ContenidoEducativo::class, Pregunta::class],
    version = 3,  // Versión actualizada
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun contenidoDao(): ContenidoEducativoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        // Migración de v1 a v2 (ya existente)
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE usuarios ADD COLUMN avatarId INTEGER NOT NULL DEFAULT -1")
            }
        }

        // Nueva migración de v2 a v3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Crear tabla de contenidos
                database.execSQL("""
            CREATE TABLE IF NOT EXISTS contenidos (
                id INTEGER PRIMARY KEY NOT NULL,
                titulo TEXT NOT NULL,
                texto TEXT NOT NULL,
                preguntas TEXT NOT NULL
            )
        """)

                // Crear tabla de preguntas (ESTA ES LA PARTE CLAVE QUE FALTABA)
                database.execSQL("""
            CREATE TABLE IF NOT EXISTS preguntas (
                id INTEGER PRIMARY KEY NOT NULL,
                contenidoId INTEGER NOT NULL,
                texto TEXT NOT NULL,
                opciones TEXT NOT NULL,
                respuestaCorrecta INTEGER NOT NULL
            )
        """)
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lectura_app.db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)  // Ambas migraciones
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}

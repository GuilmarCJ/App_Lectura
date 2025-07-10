package com.example.lecturaparaprimaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        Usuario::class,
        ContenidoEducativo::class,
        Pregunta::class,
        Progreso::class
    ],
    version = 4,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun contenidoDao(): ContenidoEducativoDao
    abstract fun progresoDao(): ProgresoDao // ✅ NUEVO DAO AGREGADO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE usuarios ADD COLUMN avatarId INTEGER NOT NULL DEFAULT -1")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS contenidos (
                        id INTEGER PRIMARY KEY NOT NULL,
                        titulo TEXT NOT NULL,
                        texto TEXT NOT NULL,
                        preguntas TEXT NOT NULL
                    )
                """)

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

        // ✅ NUEVA MIGRACIÓN para crear la tabla de progreso
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS progreso (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        nivel INTEGER NOT NULL,
                        correctas INTEGER NOT NULL,
                        incorrectas INTEGER NOT NULL,
                        nota REAL NOT NULL,
                        timestamp INTEGER NOT NULL
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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4) // ✅ Incluye todas
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
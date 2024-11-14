package com.example.projetofinal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetofinal.model.Task
import com.example.projetofinal.model.User

@Database(entities = [
    User::class
                     ], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    // Remover esta linha para não permitir acesso ao banco de dados na main thread
                    //.allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
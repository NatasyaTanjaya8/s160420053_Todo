package com.natasya.todokpb.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.natasya.todokpb.util.DB_NAME
import com.natasya.todokpb.util.MIGRATION_1_2
import com.natasya.todokpb.util.MIGRATION_2_3

@Database(entities = [Todo::class], version = 3)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private var LOCK = Any()
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, DB_NAME).addMigrations(
            MIGRATION_1_2, MIGRATION_2_3).build()
        operator fun invoke(context: Context) {
            if (instance != null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
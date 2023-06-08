package com.natasya.todokpb.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.natasya.todokpb.model.TodoDatabase

var DB_NAME = "newtododb"
fun buildDB(context: Context):TodoDatabase{
    var db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2).build()
    return db
}
var MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table todo add column priority integer default 3 not null")
        database.execSQL("insert into todo(title, notes, priority) values('Study hard', 'Play harder', 3)")
    }
}
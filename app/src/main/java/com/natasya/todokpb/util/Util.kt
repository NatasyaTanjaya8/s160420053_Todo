package com.natasya.todokpb.util

import android.content.Context
import androidx.room.Room
import com.natasya.todokpb.model.TodoDatabase

var DB_NAME = "newtododb"
fun buildDB(context: Context):TodoDatabase{
    var db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).build()
    return db
}
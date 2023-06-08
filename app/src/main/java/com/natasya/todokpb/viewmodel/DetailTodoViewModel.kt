package com.natasya.todokpb.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.natasya.todokpb.model.Todo
import com.natasya.todokpb.model.TodoDatabase
import com.natasya.todokpb.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    var todoLD = MutableLiveData<Todo>()
    fun addTodo(todo: Todo){
        launch {
            //var db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "newtododb").build()
            var db = buildDB(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(uuid: Int){
        launch {
            Log.d("uuidcek", uuid.toString())
            var db = buildDB(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
        }
    }

    fun update(title: String, notes: String, priority: Int, uuid: Int){
        launch {
            var db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }
}
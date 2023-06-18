package com.natasya.todokpb.viewmodel

import android.app.Application
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
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    var todoLD = MutableLiveData<List<Todo>>()
    var todoLoadErrorLD = MutableLiveData<Boolean>()
    var loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            var db = buildDB(getApplication())
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }
    fun clearTask(todo: Todo){
        launch {
            //var db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "newtododb").build()
            var db = buildDB(getApplication())
            db.todoDao().deleteTodo(todo)
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }
    fun updateIsDone(title: String, notes: String, priority: Int, uuid: Int, is_done: Int){
        launch {
            var db = buildDB(getApplication())
            db.todoDao().updateIsDone(title, notes, priority, is_done, uuid)
        }
    }
}
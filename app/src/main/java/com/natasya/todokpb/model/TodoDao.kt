package com.natasya.todokpb.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Todo)

    @Query("select * from todo order by priority desc")
    fun selectAllTodo(): List<Todo>

    @Query("select * from todo where uuid = :id")
    fun selectTodo(id: Int): Todo

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("update todo set title=:title, notes=:notes, priority=:priority where uuid=:id")
    fun update(title: String, notes: String, priority: Int, id: Int)

    @Query("update todo set is_done=1 where uuid=:id")
    fun updateIsDone(id: Int)
}
package com.natasya.todokpb.view

import android.view.View
import android.widget.CompoundButton
import com.natasya.todokpb.model.Todo

interface TodoItemLayoutInterface{
    fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo)
    fun onTodoEditClick(v: View)
}
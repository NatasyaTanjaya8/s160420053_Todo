package com.natasya.todokpb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.natasya.todokpb.R
import com.natasya.todokpb.databinding.TodoItemLayoutBinding
import com.natasya.todokpb.model.Todo

class TodoListAdapter(var todoList:ArrayList<Todo>, var adapterOnClick : (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(), TodoItemLayoutInterface {
    class TodoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        /*
        var view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
        */
        var view = TodoItemLayoutBinding.inflate(inflater, parent, false)
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.checklistener = this
        holder.view.editlistener = this
        holder.view.checkTask.isChecked = false
        /*
        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checktask.text = todoList[position].title
        checktask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }
        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener {
            var action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        checktask.setOnCheckedChangeListener{ compoundButton, isChecked ->
            if (isChecked == true){
                adapterOnClick(todoList[position])
            }
        }
        */
    }

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        var uuid = v.tag.toString().toInt()
        var action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}
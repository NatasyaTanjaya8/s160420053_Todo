package com.natasya.todokpb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.natasya.todokpb.R
import com.natasya.todokpb.viewmodel.ListTodoViewModel

class TodoListFragment : Fragment() {
    private lateinit var viewModel: ListTodoViewModel
    private var todoListAdapter = TodoListAdapter(arrayListOf(), { item -> viewModel.clearTask(item) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()
        var recViewTodo = view.findViewById<RecyclerView>(R.id.recViewTodo)
        recViewTodo.layoutManager = LinearLayoutManager(context)
        //var uuid =
        //var txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
        //var txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
        //var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        //var radioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        //var is_done = 1
        //viewModel.updateIsDone(txtTitle.text.toString(), txtNotes.text.toString(), radioButton.tag.toString().toInt(), is_done, uuid)
        recViewTodo.adapter = todoListAdapter
        var fabAddTodo = view.findViewById<FloatingActionButton>(R.id.fabAddTodo)
        fabAddTodo.setOnClickListener {
            var action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            var txtEmpty = view?.findViewById<TextView>(R.id.txtEmpty)
            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility = View.GONE
            }
        })
    }
}
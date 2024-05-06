package com.example.androidexam.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexam.Fragments.TodoListFragmentDirections
import com.example.androidexam.Models.TodoJob
import com.example.androidexam.R
import com.example.androidexam.databinding.FragmentTodoListBinding
import com.example.androidexam.databinding.ListItemBinding


class TodoAdapter(private val onDeleteListener: (Int) -> Unit): RecyclerView.Adapter<TodoAdapter.NameViewHolder>() {

    private val list = arrayListOf<TodoJob>()

    inner class NameViewHolder(val todoJobBinding: ListItemBinding): RecyclerView.ViewHolder(todoJobBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val todos = list[position]

        holder.todoJobBinding.textView.text = todos.task

        todos.warningimage?.let {
            holder.todoJobBinding.imageView.setImageResource(it)
        } ?: holder.todoJobBinding.imageView.setImageResource(R.drawable.ic_launcher_background)

        if (todos.islastday==false) {
            holder.todoJobBinding.textView2.text = ""
        } else {
            holder.todoJobBinding.textView2.text = "Last day"
        }

        holder.todoJobBinding.imageView2.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onDeleteListener.invoke(position)

            }
        }

        holder.todoJobBinding.materialCardView.setOnClickListener {
            Navigation.findNavController(holder.itemView)
                .navigate(TodoListFragmentDirections.actionTodoListFragmentToDetailFragment(todos))
        }
    }

    fun updateList(newList: ArrayList<TodoJob>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
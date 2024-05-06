package com.example.androidexam.Fragments

import android.R.attr.text
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexam.Adapter.TodoAdapter
import com.example.androidexam.Models.TodoJob
import com.example.androidexam.R
import com.example.androidexam.databinding.CustomdialogBinding
import com.example.androidexam.databinding.FragmentTodoListBinding


class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    private var todoJob = TodoJob()
    private var todojobList = ArrayList<TodoJob>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshui()

        binding.button.setOnClickListener {
            customAlertDialog(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun customAlertDialog(context: Context) {
        val dialogBinding = CustomdialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context).apply {
            setView(dialogBinding.root)
        }.create()

        dialog.show()

        dialogBinding.button1.setOnClickListener {
            val editTextValue = dialogBinding.editTextText.text.toString()
            val selectedRadioButtonId = dialogBinding.radioGroup.checkedRadioButtonId
            val isChecked = dialogBinding.checkBox.isChecked

            val drawableId = when (selectedRadioButtonId) {
                R.id.radioButton1 -> R.drawable.image_1
                R.id.radioButton2 -> R.drawable.image_2
                R.id.radioButton3 -> R.drawable.image_3
                else -> 0
            }

            todoJob = TodoJob(editTextValue, drawableId, isChecked)

            if(editTextValue.isNotBlank() && editTextValue.isNotEmpty()) {
                todojobList.add(todoJob)

                refreshui()


                dialog.dismiss()
            }
            else
            {
                Toast.makeText(requireContext(), "Write the name of the task", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun onDeleteItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            todojobList.removeAt(position)
            binding.recycleViewHome.adapter?.notifyItemRemoved(position)
            binding.textView.text = "Item count: ${todojobList.size}"

            (binding.recycleViewHome.adapter as? TodoAdapter)?.updateList(todojobList)
        }
    }


    private  fun refreshui(){

        val todoAdapter = TodoAdapter(::onDeleteItem)
        todoAdapter.updateList(todojobList)

        val horizontalLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleViewHome.layoutManager = horizontalLayoutManager
        binding.recycleViewHome.adapter = todoAdapter

        binding.textView.text = "Item count: ${todojobList.size}"
    }



}

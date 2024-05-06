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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidexam.Adapter.TodoAdapter
import com.example.androidexam.Models.TodoJob
import com.example.androidexam.R
import com.example.androidexam.databinding.CustomdialogBinding
import com.example.androidexam.databinding.FragmentTodoListBinding


class TodoListFragment : Fragment() {


    private var _binding: FragmentTodoListBinding? = null


    private var todoJob = TodoJob()
    private var todojobList = ArrayList<TodoJob>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.textView.text="Item count: ${todojobList.count()}"

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

            todojobList.add(todoJob)

            val todoAdapter = TodoAdapter()

            todoAdapter.updateList(todojobList)

            val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.recycleViewHome.layoutManager = horizontalLayoutManager
            binding.recycleViewHome.adapter = todoAdapter

            binding.textView.text="Item count: ${todojobList.count()}"

            dialog.dismiss()
        }
    }

}

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
import com.example.androidexam.Models.TodoJob
import com.example.androidexam.R
import com.example.androidexam.databinding.CustomdialogBinding
import com.example.androidexam.databinding.FragmentTodoListBinding


class TodoListFragment : Fragment() {


    private var _binding: FragmentTodoListBinding? = null


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



            var binding = CustomdialogBinding.inflate(LayoutInflater.from(getContext()));

            if(binding.checkBox.isChecked) {

                val selectedRadioButtonId: Int = binding.radioGroup.checkedRadioButtonId

                if(selectedRadioButtonId==1)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_1, true)
                }

                if(selectedRadioButtonId==2)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_2, true)
                }

                if(selectedRadioButtonId==3)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_3, true)
                }
            }

            if(!binding.checkBox.isChecked) {

                val selectedRadioButtonId: Int = binding.radioGroup.checkedRadioButtonId

                if(selectedRadioButtonId==1)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_1, false)
                }

                if(selectedRadioButtonId==2)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_2, false)
                }

                if(selectedRadioButtonId==3)
                {
                    var user = TodoJob(binding.editTextText.text.toString(), R.drawable.image_3, false)
                }
            }



            dialog.dismiss()
        }


    }


}
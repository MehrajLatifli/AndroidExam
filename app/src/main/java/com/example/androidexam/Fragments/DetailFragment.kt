package com.example.androidexam.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexam.Adapter.TodoAdapter
import com.example.androidexam.Models.TodoJob
import com.example.androidexam.R
import com.example.androidexam.databinding.CustomdialogBinding
import com.example.androidexam.databinding.FragmentDetailBinding
import com.example.androidexam.databinding.FragmentTodoListBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    private val args:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todo = args.todo

        binding.textView.text=todo.task?:""
        binding.imageView.setImageResource(todo.warningimage?:0)

        if(todo.islastday==false)
        {
            binding.textView2.text=""
        }
        else
        {
            binding.textView2.text="Last Day"
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
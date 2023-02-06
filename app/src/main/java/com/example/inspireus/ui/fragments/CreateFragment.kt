package com.example.inspireus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.inspireus.R
import com.example.inspireus.data.remote.QuoteDataSource
import com.example.inspireus.databinding.FragmentCreateBinding
import com.example.inspireus.domain.QuotesRepositoryImplement
import com.example.inspireus.presentation.CreateViewModel
import com.example.inspireus.presentation.CreateViewModelFactory
import com.example.inspireus.utils.Resource


class CreateFragment : Fragment(R.layout.fragment_create) {

    private lateinit var binding: FragmentCreateBinding
    private val viewModel by viewModels<CreateViewModel> { CreateViewModelFactory(QuotesRepositoryImplement(
        QuoteDataSource()
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateBinding.bind(view)

        binding.txtTitle.setOnClickListener {
            saveQuote()
        }
    }

    private fun saveQuote() {
        val newQuote = binding.editTextTextPersonName.text.toString().trim()
        viewModel.createQuote(newQuote).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "You really inspire us!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Ups, sory.. Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}
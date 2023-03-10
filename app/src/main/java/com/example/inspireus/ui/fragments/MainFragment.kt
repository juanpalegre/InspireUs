package com.example.inspireus.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.inspireus.R
import com.example.inspireus.data.local.AppDatabase
import com.example.inspireus.data.local.LocalDataSource
import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.remote.QuoteDataSource
import com.example.inspireus.databinding.FragmentMainBinding
import com.example.inspireus.domain.QuotesRepositoryImplement
import com.example.inspireus.presentation.MainViewModel
import com.example.inspireus.presentation.MainViewModelFactory
import com.example.inspireus.utils.Resource


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var quote: Quote
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory(QuotesRepositoryImplement(
        QuoteDataSource(), LocalDataSource(AppDatabase.getDatabase(requireContext()).quoteDao())
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setMainText()
        binding.textQuote.setOnClickListener {
            setMainText()
        }
        //quote.quote = binding.textQuote.text.toString()
        binding.floatingFavoriteButton.setOnClickListener {
            quote.quote = binding.textQuote.text.toString()
            viewModel.saveQuote(quote)
        }
    }

    private fun setMainText() {
        viewModel.getMainQuote().observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textQuote.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textQuote.visibility = View.VISIBLE
                    binding.textQuote.text = result.data.quote
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Sory, plis check your internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
package com.example.inspireus.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch


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
        viewModel.quote.observe(viewLifecycleOwner, Observer {
            quote = it
        })

        binding.floatingFavoriteButton.setOnClickListener {
            val currentQuote = currentQuote()
            if (currentQuote != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.saveQuote(currentQuote)
                    Log.d("database", "Frase guardada en la base de datos")
                }
            } else {
                Log.e("database", "Error al obtener el Quote desde la pantalla.")
            }
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

    private fun currentQuote(): Quote? {
        val currentQuoteText = binding.textQuote.text.toString().trim()
        return if (currentQuoteText.isNotEmpty()) {
            Quote(currentQuoteText)
        } else {
            null
        }
    }

}
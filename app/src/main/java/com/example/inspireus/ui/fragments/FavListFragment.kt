package com.example.inspireus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.inspireus.R
import com.example.inspireus.data.local.AppDatabase
import com.example.inspireus.data.local.LocalDataSource
import com.example.inspireus.data.remote.QuoteDataSource
import com.example.inspireus.databinding.FragmentFavListBinding
import com.example.inspireus.domain.QuotesRepositoryImplement
import com.example.inspireus.presentation.FavListViewModel
import com.example.inspireus.presentation.FavListViewModelFactory

class FavListFragment : Fragment(R.layout.fragment_fav_list) {

    private lateinit var binding: FragmentFavListBinding
    private val viewModel by viewModels<FavListViewModel> { FavListViewModelFactory(QuotesRepositoryImplement(
        QuoteDataSource(), LocalDataSource(AppDatabase.getDatabase(requireContext()).quoteDao())
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavListBinding.bind(view)


    }


}
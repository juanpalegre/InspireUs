package com.example.inspireus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.inspireus.R
import com.example.inspireus.databinding.FragmentFavListBinding

class FavListFragment : Fragment(R.layout.fragment_fav_list) {

    private lateinit var binding: FragmentFavListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavListBinding.bind(view)
    }


}
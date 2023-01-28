package com.example.inspireus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.inspireus.R
import com.example.inspireus.databinding.FragmentCreateBinding


class CreateFragment : Fragment(R.layout.fragment_create) {

    private lateinit var binding: FragmentCreateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateBinding.bind(view)
    }



}
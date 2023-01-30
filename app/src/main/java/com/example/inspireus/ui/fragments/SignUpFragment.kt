package com.example.inspireus.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.inspireus.R
import com.example.inspireus.databinding.FragmentSignUpBinding
import com.example.inspireus.ui.MainActivity


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        signUp()
    }

    private fun signUp() {

        binding.btnSignUp.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            if(password != confirmPassword){
                binding.etConfirmPassword.error = "Passwords does not match"
                return@setOnClickListener
            }

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(requireContext(), "Ups, please complete every items ;)", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}
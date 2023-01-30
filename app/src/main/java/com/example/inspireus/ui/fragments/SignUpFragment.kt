package com.example.inspireus.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.inspireus.R
import com.example.inspireus.data.remote.AuthDataSource
import com.example.inspireus.databinding.FragmentSignUpBinding
import com.example.inspireus.domain.AuthRepositoryImplement
import com.example.inspireus.presentation.AuthViewModel
import com.example.inspireus.presentation.AuthViewModelFactory
import com.example.inspireus.ui.MainActivity
import com.example.inspireus.utils.Resource


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory(AuthRepositoryImplement(
        AuthDataSource()
    )) }

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

            if (validateUserData(password, confirmPassword, username, email)) return@setOnClickListener
            createUser(username, email, password)
        }
    }

    private fun createUser(username: String, email: String, password: String) {
        viewModel.signUp(username, email, password).observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Sory, there was an error trying to register", Toast.LENGTH_SHORT).show()
                    Log.d("firebase", "SignUp error: ${result.exception}")
                }
            }
        })
    }

    private fun validateUserData(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {
        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Passwords does not match"
            return true
        }

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Ups, please complete every items ;)",
                Toast.LENGTH_LONG
            ).show()
            return true
        }
        return false
    }

}
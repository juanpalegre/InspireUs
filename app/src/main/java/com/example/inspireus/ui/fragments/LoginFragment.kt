package com.example.inspireus.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.inspireus.R
import com.example.inspireus.data.remote.AuthDataSource
import com.example.inspireus.databinding.FragmentLoginBinding
import com.example.inspireus.domain.AuthRepositoryImplement
import com.example.inspireus.presentation.LoginViewModel
import com.example.inspireus.presentation.LoginViewModelFactory
import com.example.inspireus.ui.MainActivity
import com.example.inspireus.utils.Resource
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginViewModel> { LoginViewModelFactory(AuthRepositoryImplement(
        AuthDataSource()
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        goToSignUpPage()
        doLogin()
    }

    private fun goToSignUpPage(){
        binding.btnSingup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun doLogin(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {

        viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Resource.Loading -> {
                    //binding.btnLogin.isEnabled = false
                }
                is Resource.Success -> {
                    //binding.btnLogin.isEnabled = true
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Usuario no registrado", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()){
            binding.etEmail.error = "Debe ingresar un email válido"
            return
            }
        if (password.isEmpty()){
            binding.etPassword.error = "Debe ingresar un password válido"
            return
        }
    }


}
package com.example.inspireus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.inspireus.domain.AuthRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo: AuthRepository): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        }catch (e: java.lang.Exception){
            emit(Resource.Failure(e))
        }
    }

    fun signUp(username: String, email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signUp(username, email, password)))
        }catch (e: java.lang.Exception){
            emit(Resource.Failure(e))
        }
    }
}

class AuthViewModelFactory(private val repo: AuthRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repo) as T
    }
}
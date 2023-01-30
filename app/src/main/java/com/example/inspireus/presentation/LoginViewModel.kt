package com.example.inspireus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.inspireus.domain.AuthRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repo: AuthRepository): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        }catch (e: java.lang.Exception){
            emit(Resource.Failure(e))
        }
    }

}

class LoginViewModelFactory(private val repo: AuthRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repo) as T
    }
}
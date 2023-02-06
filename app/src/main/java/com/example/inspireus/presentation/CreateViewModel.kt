package com.example.inspireus.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.inspireus.domain.QuotesRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers

class CreateViewModel(private val repo:QuotesRepository): ViewModel() {

    val quote = MutableLiveData<String>()

    fun createQuote(quote: String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.createQuote(quote)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class CreateViewModelFactory(private val repo: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateViewModel(repo) as T
    }
}

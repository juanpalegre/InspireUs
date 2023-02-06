package com.example.inspireus.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.inspireus.data.model.Quote
import com.example.inspireus.domain.QuotesRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: QuotesRepository): ViewModel() {

    val quoteModel = MutableLiveData<Quote>()

    fun getMainQuote() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getMainQuote()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class MainViewModelFactory(private val repo: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}
package com.example.inspireus.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteEntity
import com.example.inspireus.domain.QuotesRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: QuotesRepository): ViewModel() {

    val quote = MutableLiveData<Quote>()

    fun getMainQuote() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            val mainQuote = repo.getMainQuote()
            emit(Resource.Success(mainQuote))
            Log.d("database", "Main quote: $mainQuote")
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun saveQuote(quote: Quote){
        viewModelScope.launch {
            repo.saveQuote(quote)
            Log.d("database", "Frase guardada en la base de datos")
        }
    }

}

class MainViewModelFactory(private val repo: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}
package com.example.inspireus.presentation

import androidx.lifecycle.*
import com.example.inspireus.domain.QuotesRepository
import com.example.inspireus.utils.Resource
import kotlinx.coroutines.Dispatchers

class FavListViewModel(private val repo: QuotesRepository): ViewModel() {

    fun getSavedQuotes() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getFavQuotesList()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class FavListViewModelFactory(private val repo: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavListViewModel(repo) as T
    }
}
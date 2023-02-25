package com.example.inspireus.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.inspireus.data.model.*

class LocalDataSource(private val quoteDao: QuoteDao) {

    suspend fun getSavedQuotes(): LiveData<List<Quote>> {
        return quoteDao.getFavouritesQuotes().map { it.toListOfQuote() }
    }

    suspend fun getFavListQuotes(): List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getFavQuotesList()
        return response.map { it.toQuote() }
    }

    suspend fun saveQuote(quote: Quote){
        return quoteDao.saveQuote(quote.toQuoteEntity())
    }

    suspend fun deleteQuote(quote: Quote){
        return quoteDao.deleteQuote(quote.toQuoteEntity())
    }

}
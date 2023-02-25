package com.example.inspireus.domain

import androidx.lifecycle.LiveData
import com.example.inspireus.data.local.LocalDataSource
import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteEntity
import com.example.inspireus.data.model.QuoteList
import com.example.inspireus.data.remote.QuoteDataSource
import com.example.inspireus.utils.Resource

class QuotesRepositoryImplement(private val dataSource: QuoteDataSource, private val localDataSource: LocalDataSource): QuotesRepository {

    override suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    override suspend fun getMainQuote(): Quote = dataSource.getMainQuote()

    override suspend fun createQuote(quote: String){
        dataSource.createQuote(quote)
    }

    override suspend fun saveQuote(quote: Quote) {
        return localDataSource.saveQuote(quote)
    }

    override suspend fun getSavedQuotes(): LiveData<List<Quote>> {
        return localDataSource.getSavedQuotes()
    }

    override suspend fun getFavQuotesList(): List<Quote> {
        return localDataSource.getFavListQuotes()
    }


}
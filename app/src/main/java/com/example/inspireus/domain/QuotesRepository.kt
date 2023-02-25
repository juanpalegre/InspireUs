package com.example.inspireus.domain

import androidx.lifecycle.LiveData
import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteList
import com.example.inspireus.utils.Resource

interface QuotesRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun getMainQuote(): Quote

    suspend fun createQuote(quote: String)

    suspend fun saveQuote(quote: Quote)

    suspend fun getSavedQuotes(): LiveData<List<Quote>>

    suspend fun getFavQuotesList(): List<Quote>
}
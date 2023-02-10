package com.example.inspireus.domain

import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteList
import com.example.inspireus.utils.Resource

interface QuotesRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun getMainQuote(): Quote

    suspend fun createQuote(quote: String)

    suspend fun saveQuote(quote: Quote)

    suspend fun getSavedQuote(): QuoteList
}
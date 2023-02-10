package com.example.inspireus.domain

import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteEntity
import com.example.inspireus.data.model.QuoteList

interface SavedQuotesRepository {

    suspend fun getSavedQuotes(): QuoteList

}
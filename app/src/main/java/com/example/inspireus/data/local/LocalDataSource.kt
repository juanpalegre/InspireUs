package com.example.inspireus.data.local

import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteList
import com.example.inspireus.data.model.toQuoteEntity
import com.example.inspireus.data.model.toQuoteList

class LocalDataSource(private val quoteDao: QuoteDao) {

    suspend fun getSavedQuotes(): QuoteList {
        return quoteDao.getFavouritesQuotes().toQuoteList()
    }

    suspend fun saveQuote(quote: Quote){
        return quoteDao.saveQuote(quote.toQuoteEntity())
    }

    suspend fun deleteQuote(quote: Quote){
        return quoteDao.deleteQuote(quote.toQuoteEntity())
    }

}
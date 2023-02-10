package com.example.inspireus.domain

import com.example.inspireus.data.local.LocalDataSource
import com.example.inspireus.data.model.QuoteEntity
import com.example.inspireus.data.model.QuoteList

class SavedQuotesRepoImplement(private val localDataSource: LocalDataSource): SavedQuotesRepository {

    override suspend fun getSavedQuotes(): QuoteList {
        return localDataSource.getSavedQuotes()
    }
}
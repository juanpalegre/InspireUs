package com.example.inspireus.domain

import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.remote.QuoteDataSource
import com.example.inspireus.utils.Resource

class QuotesRepositoryImplement(private val dataSource: QuoteDataSource): QuotesRepository {

    override suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    override suspend fun getMainQuote(): Quote = dataSource.getMainQuote()
}
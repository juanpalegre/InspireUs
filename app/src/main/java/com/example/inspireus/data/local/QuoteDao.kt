package com.example.inspireus.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inspireus.data.model.Quote
import com.example.inspireus.data.model.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes_table")
    fun getFavouritesQuotes(): LiveData<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuote(quote: QuoteEntity)

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes_table")
    suspend fun getFavQuotesList(): List<QuoteEntity>
}
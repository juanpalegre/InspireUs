package com.example.inspireus.data.local

import androidx.room.*
import com.example.inspireus.data.model.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes_table")
    suspend fun getFavouritesQuotes(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuote(quote: QuoteEntity)

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)

}
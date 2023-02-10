package com.example.inspireus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Quote(
    val quote: String = ""
)

data class QuoteList (
    val results: List<Quote> = listOf()
)

@Entity(tableName = "quotes_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("quote")
    val quote: String = ""
)

fun QuoteEntity.toQuote(): Quote = Quote(
    this.quote
)

fun Quote.toQuoteEntity(): QuoteEntity = QuoteEntity(
    this.hashCode(), //revisar
    this.quote
)

fun List<QuoteEntity>.toQuoteList(): QuoteList {
    val resultList = mutableListOf<Quote>()
    this.forEach {
        resultList.add(it.toQuote())
    }
    return QuoteList(resultList)
}

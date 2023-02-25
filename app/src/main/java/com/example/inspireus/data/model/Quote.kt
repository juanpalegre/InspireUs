package com.example.inspireus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Quote(
    //var id: String = "",
    var quote: String = ""
)

data class QuoteList (
    val results: List<Quote> = listOf()
)

@Entity(tableName = "quotes_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "quote") val quote: String
)

fun QuoteEntity.toQuote(): Quote = Quote(
    quote = this.quote
)

fun Quote.toQuoteEntity(): QuoteEntity = QuoteEntity(
    quote = this.quote
)

fun List<QuoteEntity>.toQuoteList(): QuoteList {
    val resultList = mutableListOf<Quote>()
    this.forEach {
        resultList.add(it.toQuote())
    }
    return QuoteList(resultList)
}

fun List<QuoteEntity>.toListOfQuote(): List<Quote> = this.map {
    Quote(it.quote)
}

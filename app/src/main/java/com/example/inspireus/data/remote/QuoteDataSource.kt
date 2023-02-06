package com.example.inspireus.data.remote

import com.example.inspireus.data.model.Quote
import com.example.inspireus.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class QuoteDataSource {

    suspend fun getQuotes():List<Quote>{
        val quoteList = mutableListOf<Quote>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("quotes").get().await()
        for (quote in querySnapshot.documents){
            quote.toObject(Quote::class.java)?.let { quote: Quote ->
                quoteList.add(quote)
            }
        }
        return quoteList
    }

    suspend fun getMainQuote(): Quote{
        val mainList = mutableListOf<Quote>()
        val db = FirebaseFirestore.getInstance().collection("quotes").get().await()
        for(quote in db.documents){
            quote.toObject(Quote::class.java)?.let {
                mainList.add(it)
            }
        }
        val randomNumber = (mainList.indices).random()
        return mainList[randomNumber]
    }

}
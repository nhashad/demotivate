package com.example.demotivate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demotivate.QuotesQuery
import com.example.demotivate.adapter.QuotesQuery_ResponseAdapter

class QuotesViewModel: ViewModel() {

    private lateinit var quotes: List<QuotesQuery.Quote>
    private val quoteData = MutableLiveData<QuotesQuery.Quote>()
//    private val quotes = QuotesProvider().getQuotes()

    fun setQuotes (quotes: List<QuotesQuery.Quote>) {
        this.quotes =  quotes
    }

    fun updateAndGetQuoteData (): LiveData<QuotesQuery.Quote> {
        val currentIndex = randomIndex(quotes.size)

        quoteData.value = quotes[currentIndex]

        return quoteData
    }
}

fun randomIndex (size: Int) = (0 until size).random()
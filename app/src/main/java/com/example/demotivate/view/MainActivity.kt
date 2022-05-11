package com.example.demotivate.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.demotivate.R
import com.example.demotivate.databinding.ActivityMainBinding
import com.example.demotivate.viewmodel.QuotesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: QuotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initialFragment = InitialFragment()
        val quoteFragment = QuoteFragment()

        var isFirstClick = true

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, initialFragment)
            commit()
        }

        binding.button.isEnabled = false
        model.getQuotesFromAPI()
        binding.button.isEnabled = true

        binding.button.setOnClickListener {
            model.updateAndGetQuoteData().observe(this) { currentQuote ->
                if (isFirstClick) {
                    isFirstClick = false
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment, quoteFragment)
                        commit()
                    }
                } else {
                    "\" ${currentQuote.quote}\"".also { quoteFragment.binding.quoteTextView.text = it }
                    "- ${currentQuote.author}".also { quoteFragment.binding.authorTextView.text = it }
                }
            }
        }
    }
}

package com.example.wikipedia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@ObsoleteCoroutinesApi
class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var service:WikipediaService
    private val receiveChannel = Channel<Article>()
    private val history:History = History()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = WikipediaService(this, receiveChannel)
        findViewById<Button>(R.id.historyButton).setOnClickListener { showHistory() }
    }

    private fun showHistory() {
        val intent = Intent(this, HistoricActivity::class.java)

        /*
        intent.putExtra("list", history.getAllArticles().toTypedArray())
        startActivity(intent)
        */

        launch {
            for(content:Article in receiveChannel) {
                history.AddArticle(content)
                intent.putExtra("list", history.getAllArticles().toTypedArray())
                startActivity(intent)


            }
        }

        launch {
            service.getContent()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

package com.example.wikipedia

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.wikipedia.History
import com.example.wikipedia.ListArticleAdapter
import com.example.wikipedia.R
import kotlinx.coroutines.cancel

class HistoricActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)

        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val articles: List<Article> = intent
            .getParcelableArrayExtra("list")
            .map { it as Article }

        val result = arrayListOf<Article>()
        result.addAll(articles)

        val adapter = ListArticleAdapter(result)
        rv.adapter = adapter
    }

    fun delete(v: View) {
        super.onDestroy()
    }

}

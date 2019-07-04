package com.example.wikipedia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ListArticleAdapter(private val articleList: ArrayList<Article>): RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = articleList[position].title
        holder.itemSummary.text = articleList[position].summary
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemSummary: TextView = itemView.findViewById(R.id.itemSumary)
    }

}
package com.example.wikipedia

class History {

    private val articles:MutableList<Article> = mutableListOf()

    fun AddArticle(article:Article) {
        articles.add(article)
    }

    fun getAllArticles():List<Article> {
        return articles
            .toList()
    }

    fun getArticleByTitle(title:String):Article? {
        return articles
            .asSequence()
            .find { it.title == title }
    }

    fun deleteArticleFromHistory(title:String):List<Article>? {
        val article:Article? = articles
            .asSequence()
            .find { it.title == title }

        if (article != null) {
            articles.remove(article)
            return articles.toList()
        }

        return null
    }
}
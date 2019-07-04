package com.example.wikipedia

import kotlinx.coroutines.channels.SendChannel
import okhttp3.OkHttpClient
import kotlinx.coroutines.*
import okhttp3.Response
import okhttp3.Request
import org.json.JSONObject

@ObsoleteCoroutinesApi
class WikipediaService(private val coroutineScope: CoroutineScope, private val receiveChannel: SendChannel<Article>) {

    private val client:OkHttpClient = OkHttpClient()

    suspend fun getContent() = withContext(Dispatchers.Default) {
        var address = "${WikipediaUrl.BASE}/${WikipediaUrl.RANDOM}"
        var request: Request = Request
            .Builder()
            .url(address)
            .build()

        var response:Response = client
            .newCall(request)
            .execute()

        if (response.code() == 200) {
            val jsonData:String = response
                .body()!!
                .string()
                .toString()

            val jObject = JSONObject(jsonData)
            val title:String = jObject["title"].toString()
            val summary:String = jObject["extract"].toString()

            address = "${WikipediaUrl.BASE}/${WikipediaUrl.HTMLCONTENT}/$title"

            request = Request
                .Builder()
                .url(address)
                .build()

            response = client
                .newCall(request)
                .execute()

            if (response.code() == 200) {
                val htmlContent:String = response
                    .body()!!
                    .string()
                    .toString()

                receiveChannel.send(Article(title, summary, htmlContent))
            }
        }
    }
}
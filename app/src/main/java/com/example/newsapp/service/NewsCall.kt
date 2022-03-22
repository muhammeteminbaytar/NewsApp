package com.example.newsapp.service

import com.example.newsapp.R
import com.example.newsapp.model.NewsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

class NewsCall(BASE_URL: String) {
    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val service=retrofit.create(NewsAPI::class.java)
        fun getCallData(country:String,category:String,apiKey:String):Call<NewsModel>{
            var call=service.getNewsData(country,category,apiKey)
        return call
    }
}
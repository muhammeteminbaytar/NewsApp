package com.example.newsapp.service

import com.example.newsapp.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    //https://newsapi.org/v2/
    // top-headlines?country=tr&apiKey=506272ef6b1d4fb1b2844d4fbc7d3dd6
    @GET("top-headlines?country=tr&apiKey=506272ef6b1d4fb1b2844d4fbc7d3dd6")

    fun getNewsData():Call<NewsModel>
}
package com.example.newsapp.service

import com.example.newsapp.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsAPI {
    //https://newsapi.org/v2/
    // top-headlines?country=tr&apiKey=506272ef6b1d4fb1b2844d4fbc7d3dd6
    //top-headlines?country=de&category=business&apiKey=506272ef6b1d4fb1b2844d4fbc7d3dd6
    @GET("top-headlines")
    fun getNewsData(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey:String
    ):Call<NewsModel>
}
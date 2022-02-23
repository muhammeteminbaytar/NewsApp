package com.example.newsapp.model

data class ArticlesModel(val source:SourceModel, val author:String, val title:String, val description:String,
                         val url:String,
                         var urlToImage:String, val publishedAt:String, val content:String)

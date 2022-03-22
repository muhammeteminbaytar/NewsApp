package com.example.newsapp.ui.component.news

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.ArticlesModel
import com.example.newsapp.model.CatagoryModel
import com.example.newsapp.model.NewsModel
import com.example.newsapp.service.NewsCall
import com.example.newsapp.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:BaseViewModel() {

    var newsData=MutableLiveData<List<ArticlesModel>>()
    var country=MutableLiveData<String>()
    var catagory=MutableLiveData<String>()
    init {
        country=MutableLiveData("tr")
        catagory=MutableLiveData("general")
    }

    fun loadData(countryID:String,category:String,baseUrl:String,apiKey:String){
        val call= NewsCall(baseUrl)
        call.getCallData(countryID,category,apiKey).enqueue(object:
            Callback<NewsModel> {
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        newsData.value=it.articles
                    }
                }
            }
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                t.printStackTrace()
                println(t)
            }
        })
    }

    fun createCatagoryList(): List<CatagoryModel> {
        return mutableListOf(
            CatagoryModel("general"),
            CatagoryModel("business"),
            CatagoryModel("entertainment"),
            CatagoryModel("health"),
            CatagoryModel("science"),
            CatagoryModel("sports"),
            CatagoryModel("technology")
        )
    }
}
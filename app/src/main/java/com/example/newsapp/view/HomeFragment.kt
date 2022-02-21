package com.example.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.RecyclerAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.model.NewsModel
import com.example.newsapp.service.NewsAPI
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loadData()
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(NewsAPI::class.java)
        val call=service.getNewsData()
        call.enqueue(object: Callback<NewsModel>{
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        binding.recyclerHome.layoutManager=LinearLayoutManager(view?.context)
                        binding.recyclerHome.adapter=RecyclerAdapter(it.articles)
                        for (i in it.articles){
                            println(i.title)
                        }
                    }
                }
             }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    t.printStackTrace()
                      println(t)
                }

        })

    }



}
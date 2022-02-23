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
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loadData()
        auth = Firebase.auth
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        viewEvent()
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
                    }
                }
             }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    t.printStackTrace()
                      println(t)
                }

        })
    }

    private fun viewEvent(){
        binding.imgProfile.setOnClickListener {
            if (auth.currentUser==null){
                Navigation.findNavController(it).navigate(R.id.homeTOlogin)
            }else{
                Navigation.findNavController(it).navigate(R.id.homeTOprofile)
            }
        }
    }
}
package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.CardLayoutBinding
import com.example.newsapp.model.ArticlesModel
import com.example.newsapp.model.NewsModel

class RecyclerAdapter(private val articlesList:List<ArticlesModel>):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=DataBindingUtil.inflate<CardLayoutBinding>(inflater,R.layout.card_layout,parent,false)
        return ViewHolder(binding)
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.listItem=articlesList[position]
    }

    override fun getItemCount(): Int {
            return articlesList.size
        }
}
class ViewHolder(val binding: CardLayoutBinding):RecyclerView.ViewHolder(binding.root){
}
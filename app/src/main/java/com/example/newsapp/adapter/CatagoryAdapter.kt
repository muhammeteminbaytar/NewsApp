package com.example.newsapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.CatagoryModel

class CatagoryAdapter(private val catagoryList:List<CatagoryModel>,val clickListener:(CatagoryModel)->Unit):RecyclerView.Adapter<CatagoryAdapter.ViewHolder>(){

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var catagory:TextView=itemView.findViewById(R.id.txt_catagory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatagoryAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.catagory_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatagoryAdapter.ViewHolder, position: Int) {
        var data = catagoryList[position]
        holder.catagory.text=data.catagoryName
        holder.itemView.setOnClickListener {
            clickListener(data)
        }
    }

    override fun getItemCount()=catagoryList.size

}
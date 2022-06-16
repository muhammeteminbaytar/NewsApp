package com.example.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.CountryModel

class CountryAdapter(var context: Context,val clickListener:(CountryModel)->Unit): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    var dataList = emptyList<CountryModel>()

    internal fun setDataList(dataList : List<CountryModel>){
        this.dataList=dataList
        notifyDataSetChanged()
    }

   inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var image:ImageView = itemView.findViewById(R.id.img_country)
        var countryName:TextView = itemView.findViewById(R.id.txt_country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
            var data = dataList[position]

            holder.countryName.text=data.countryName
            holder.image.setImageResource(data.image)

            holder.itemView.setOnClickListener {
                clickListener(data)
            }
    }
    override fun getItemCount()=dataList.size
}
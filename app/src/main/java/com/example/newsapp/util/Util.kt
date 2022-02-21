package com.example.newsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:downloadUrl")
fun downloadUrl(view: ImageView,url:String){
    Picasso.with(view.context).load(url).into(view)
}
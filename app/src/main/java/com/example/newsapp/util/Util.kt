package com.example.newsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("android:downloadUrl")
fun downloadUrl(view: ImageView,url:String){
        try {
                Picasso.with(view.context).load(url).into(view)
        }catch (e:Exception){}

}
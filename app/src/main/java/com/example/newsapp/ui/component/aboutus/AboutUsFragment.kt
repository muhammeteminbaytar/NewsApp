package com.example.newsapp.ui.component.aboutus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.ui.base.BaseFragment

class AboutUsFragment : BaseFragment() {

    val intent = Intent().apply {
        data = Uri.parse("example.com/havarnews/about")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }
}
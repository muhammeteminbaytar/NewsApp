package com.example.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentDetailNewsBinding
import com.example.newsapp.util.downloadUrl


class DetailNewsFragment : Fragment() {

    private lateinit var binding:FragmentDetailNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundleData()
    }

   private fun bundleData(){
        val bundle:DetailNewsFragmentArgs by navArgs()
       binding.txtTitle.text=bundle.title
       binding.txtDate.text=bundle.date
       binding.txtDesc.text=bundle.description
       downloadUrl(binding.imgBg, bundle.image)
    }
}
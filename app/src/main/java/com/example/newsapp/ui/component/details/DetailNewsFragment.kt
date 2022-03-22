package com.example.newsapp.ui.component.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentDetailNewsBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.util.downloadUrl

class DetailNewsFragment : BaseFragment() {

    override var useSharedViewModel = true

    val viewModel : DetailViewModel  by viewModels()

    private lateinit var binding:FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentDetailNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundleData()
        initView()
    }

    private fun initView(){
    }

   private fun bundleData(){
       val bundle:DetailNewsFragmentArgs by navArgs()
       binding.txtTitle.text=bundle.title
       binding.txtDate.text=bundle.date
       binding.txtDesc.text=bundle.description
       downloadUrl(binding.imgBg, bundle.image)
    }
}
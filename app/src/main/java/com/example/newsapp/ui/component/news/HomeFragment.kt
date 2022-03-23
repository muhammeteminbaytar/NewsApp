package com.example.newsapp.ui.component.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.RecyclerAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import androidx.navigation.Navigation
import com.example.newsapp.adapter.CatagoryAdapter
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.model.ArticlesModel
import com.example.newsapp.model.CatagoryModel

class HomeFragment : BaseFragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel:NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initView()
        getFilterData()
        catchCountryData()
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        categoryRecylerControl()
        return binding.root
    }
    private fun getFilterData(){
        viewModel.country.observe(this,{country->
            viewModel.category.observe(this,{ catagory->
                frgLoadData(country,catagory)
            })
        })
    }
    private fun initView(){
        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
    }
    private fun catchCountryData(){
      requireActivity().supportFragmentManager.setFragmentResultListener("requestKey",viewLifecycleOwner){ requestKey, bundle ->
                val result=bundle.getString("bundleKey")
                viewModel.category.observe(this,{
                    frgLoadData(result.toString(),it)
                })
                    viewModel.country.value=result.toString()
            }
    }

    private fun categoryRecylerControl(){
        binding.recylerCatagory.layoutManager=LinearLayoutManager(view?.context,LinearLayoutManager.HORIZONTAL,false)
        binding.recylerCatagory.adapter=CatagoryAdapter(viewModel.createCategoryList()){
            catagoryClick(it)
        }
    }

    private fun catagoryClick(data:CatagoryModel){
        viewModel.category.value=data.catagoryName
        viewModel.country.observe(this,{country->
            viewModel.category.observe(this,{ catagory->
                frgLoadData(country,catagory)
            })
        })
    }
    private fun frgLoadData(countryID:String,catagory:String){
        viewModel.loadData(countryID,catagory,getString(R.string.Base_Url),getString(R.string.newsApiKey))
        viewModel.newsData.observe(this,{
               binding.recyclerHome.layoutManager= LinearLayoutManager(view?.context)
                        binding.recyclerHome.adapter= RecyclerAdapter(it){
                            recylerClick(it)
                        }
        })
    }

    private fun recylerClick(data:ArticlesModel){
        var title=""
        var date=""
        var content=""
        var urlToImage=""

        if (data.title!=null||data.publishedAt!=null||data.content!=null||data.urlToImage!=null){
            title=data.title.toString()
            date=data.publishedAt.toString()
            content=data.content.toString()
            urlToImage=data.urlToImage.toString()
        }
        val transition=HomeFragmentDirections.homeTOdetail(title,date,content,urlToImage)
        Navigation.findNavController(binding.root).navigate(transition)
    }
}
package com.example.newsapp.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.CountryAdapter
import com.example.newsapp.model.CountryModel

class CustomCountryClass: DialogFragment() {

    private lateinit var rvCountry: RecyclerView
    private lateinit var countryAdapter:CountryAdapter
    private var dataList= mutableListOf<CountryModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            val rootView:View=inflater.inflate(R.layout.country_custom_dialog,container,false)
            recyclerControl(rootView)
            return rootView
    }

    private fun recyclerControl(rootView:View){
        rvCountry=rootView.findViewById(R.id.rv_country)
        rvCountry.layoutManager= GridLayoutManager(context,2)
        countryAdapter = CountryAdapter(requireContext()){
            recyclerClick(it)
        }
        rvCountry.adapter=countryAdapter
        listCrater()
    }
    private fun recyclerClick(data:CountryModel){
        val result=data.countryName
        requireActivity().supportFragmentManager.setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        dialog?.cancel()
    }


    private fun listCrater(){

        dataList.add(CountryModel(R.drawable.ae,"ae"))
        dataList.add(CountryModel(R.drawable.ar,"ar"))
        dataList.add(CountryModel(R.drawable.au,"au"))
        dataList.add(CountryModel(R.drawable.av,"av"))
        dataList.add(CountryModel(R.drawable.be,"be"))
        dataList.add(CountryModel(R.drawable.bg,"bg"))
        dataList.add(CountryModel(R.drawable.br,"br"))
        dataList.add(CountryModel(R.drawable.ca,"ca"))
        dataList.add(CountryModel(R.drawable.ch,"ch"))
        dataList.add(CountryModel(R.drawable.cn,"cn"))
        dataList.add(CountryModel(R.drawable.co,"co"))
        dataList.add(CountryModel(R.drawable.cu,"cu"))
        dataList.add(CountryModel(R.drawable.cz,"cz"))
        dataList.add(CountryModel(R.drawable.de,"de"))
        dataList.add(CountryModel(R.drawable.eg,"eg"))
        dataList.add(CountryModel(R.drawable.fr,"fr"))
        dataList.add(CountryModel(R.drawable.gb,"gb"))
        dataList.add(CountryModel(R.drawable.gr,"gr"))
        dataList.add(CountryModel(R.drawable.hk,"hk"))
        dataList.add(CountryModel(R.drawable.hu,"hu"))
        dataList.add(CountryModel(R.drawable.id,"id"))
        dataList.add(CountryModel(R.drawable.ie,"ie"))
        dataList.add(CountryModel(R.drawable.il,"il"))
        dataList.add(CountryModel(R.drawable.`in`,"in"))
        dataList.add(CountryModel(R.drawable.it,"it"))
        dataList.add(CountryModel(R.drawable.jp,"jp"))
        dataList.add(CountryModel(R.drawable.kr,"kr"))
        dataList.add(CountryModel(R.drawable.lt,"lt"))
        dataList.add(CountryModel(R.drawable.lv,"lv"))
        dataList.add(CountryModel(R.drawable.ma,"ma"))
        dataList.add(CountryModel(R.drawable.mx,"mx"))
        dataList.add(CountryModel(R.drawable.my,"my"))
        dataList.add(CountryModel(R.drawable.ng,"ng"))
        dataList.add(CountryModel(R.drawable.nl,"nl"))
        dataList.add(CountryModel(R.drawable.no,"no"))
        dataList.add(CountryModel(R.drawable.nz,"nz"))
        dataList.add(CountryModel(R.drawable.ph,"ph"))
        dataList.add(CountryModel(R.drawable.pl,"pl"))
        dataList.add(CountryModel(R.drawable.pt,"pt"))
        dataList.add(CountryModel(R.drawable.ro,"ro"))
        dataList.add(CountryModel(R.drawable.rs,"rs"))
        dataList.add(CountryModel(R.drawable.ru,"ru"))
        dataList.add(CountryModel(R.drawable.sa,"sa"))
        dataList.add(CountryModel(R.drawable.se,"se"))
        dataList.add(CountryModel(R.drawable.sg,"sg"))
        dataList.add(CountryModel(R.drawable.si,"si"))
        dataList.add(CountryModel(R.drawable.sk,"sk"))
        dataList.add(CountryModel(R.drawable.th,"th"))
        dataList.add(CountryModel(R.drawable.tr,"tr"))
        dataList.add(CountryModel(R.drawable.tw,"tw"))
        dataList.add(CountryModel(R.drawable.ua,"ua"))
        dataList.add(CountryModel(R.drawable.us,"us"))
        dataList.add(CountryModel(R.drawable.ve,"ve"))
        dataList.add(CountryModel(R.drawable.za,"za"))

        countryAdapter.setDataList(dataList)
    }

}
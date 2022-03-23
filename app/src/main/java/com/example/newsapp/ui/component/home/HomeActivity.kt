package com.example.newsapp.ui.component.home

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.component.aboutus.AboutUsFragment
import com.example.newsapp.ui.component.profile.ProfileFragment
import com.example.newsapp.util.CustomCountryClass

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_home)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navView,navHostFragment.navController)

        val toggle = ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.inflateHeaderView(R.layout.navigation_header)
        navController()
        deeplinkController()

        viewModel=ViewModelProvider(this)[HomeActViewModel::class.java]
    }
    private fun navController(){
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.country-> countrySelect()
                R.id.profilFragment-> connectFragment(ProfileFragment(),binding)
                R.id.AboutUsFragment->connectFragment(AboutUsFragment(),binding)
            }
            true
        }
    }
    private fun deeplinkController(){
        if(intent.getBooleanExtra("deeplinkopen",false)){
            connectFragment(AboutUsFragment(),binding)
        }
    }

    private fun countrySelect(){
        var dialog= CustomCountryClass()
        dialog.show(this.supportFragmentManager,"Country Custom Dialog")
    }

    override fun onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
}
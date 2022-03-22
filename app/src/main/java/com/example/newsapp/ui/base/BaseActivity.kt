package com.example.newsapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding

open class BaseActivity: AppCompatActivity() {

    protected fun connectFragment(fragment: Fragment,binding: ActivityHomeBinding) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit()
        drawerEventControl(binding)
    }
    private fun drawerEventControl(binding:ActivityHomeBinding){
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }
    }
}
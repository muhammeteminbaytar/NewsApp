package com.example.newsapp.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.example.newsapp.R
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.component.home.HomeActivity

class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainintent= Intent(this@MainActivity, HomeActivity::class.java)

        if (intent.data?.toString()!=null) {
            println(intent.data.toString())
            mainintent.putExtra("deeplinkopen",true)
       }

        supportActionBar?.hide()
        val timer = object: CountDownTimer(2500,2500) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                startActivity(mainintent)
                finish()
            }
        }
        timer.start()
    }
}
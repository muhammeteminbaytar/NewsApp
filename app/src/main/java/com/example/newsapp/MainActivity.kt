package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val timer = object: CountDownTimer(2500,2500) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intent= Intent(this@MainActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()
    }
}
package com.example.punkbeers.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.punkbeers.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen(this)
    }

    private fun scheduleSplashScreen(context: Context){
        Handler(Looper.getMainLooper()).postDelayed({
            context.startActivity(Intent(context, MainActivity::class.java))
            finish()
        }, 2000)
    }

}
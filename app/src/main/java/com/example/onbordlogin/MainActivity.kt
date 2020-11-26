package com.example.onbordlogin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(
            {
                val i = Intent(this@MainActivity, SecoundActivity::class.java)
                startActivity(i)
                finish()
            }, SPLASH_TIME_OUT
        )


    }

}
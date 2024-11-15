package com.example.bookingapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bookingapp.ui.SideBarActivity
import com.example.bookingapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay for 3 seconds
        Handler().postDelayed({
            // Start the main activity
            val intent = Intent(this, SideBarActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
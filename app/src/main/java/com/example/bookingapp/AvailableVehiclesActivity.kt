package com.example.bookingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class AvailableVehiclesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_vehicles)

        val btnBackSignUp: AppCompatImageView = findViewById(R.id.btnBack)
        btnBackSignUp.setOnClickListener(){
            finish()
        }
    }
}

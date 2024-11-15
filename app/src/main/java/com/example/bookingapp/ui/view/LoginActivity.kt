package com.example.bookingapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.bookingapp.ui.MainActivity
import com.example.bookingapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val txtDescription4Login: TextView = findViewById(R.id.txtDescription4Login)
        txtDescription4Login.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnBackLogin: AppCompatImageView = findViewById(R.id.btnBack)
        btnBackLogin.setOnClickListener(){
            finish()
        }
    }
}
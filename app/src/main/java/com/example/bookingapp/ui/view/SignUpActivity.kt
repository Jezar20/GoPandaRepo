package com.example.bookingapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bookingapp.databinding.ActivitySignupBinding
import com.example.bookingapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener{
            val phoneNumber = binding.txtfieldInputNumberSignUp.text.toString()
            val age = binding.txtfieldInputAgeSignUp.text.toString()
            val googleAuthToken = "Google Auth Token"
            userViewModel.signup(phoneNumber,age,googleAuthToken)
        }

        userViewModel.user.observe(this, Observer { user ->
            if (user != null) {
                if (binding.txtfieldInputNumberSignUp.text.toString() != user.phoneNumber && user.age!! >= 18) {
                    Toast.makeText(this, "SignUp Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Call finish() to ensure the SignUpActivity is closed
                } else if (binding.txtfieldInputNumberSignUp.text.toString() == user.phoneNumber) {
                    Toast.makeText(this, "Phone Number already exists", Toast.LENGTH_SHORT).show()
                } else if (user.age!! < 18) {
                    Toast.makeText(this, "You must be 18+ to register", Toast.LENGTH_SHORT).show()
                }
                userViewModel.resetState() // Reset state after processing
            } else {
                Toast.makeText(this, "SignUp Failed", Toast.LENGTH_SHORT).show()
            }
        })


        binding.txtDescription4SignUp.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener(){
            finish()
        }
    }
}
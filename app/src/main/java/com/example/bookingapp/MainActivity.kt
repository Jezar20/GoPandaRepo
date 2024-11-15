package com.example.bookingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel.fetchUsers()

//        viewModel.userPhoneNumber.observe(this@MainActivity){
//            Log.i("TAG", "${it}")
//        }


    }
}
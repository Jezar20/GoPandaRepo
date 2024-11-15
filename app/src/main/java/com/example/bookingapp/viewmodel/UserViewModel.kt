package com.example.bookingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bookingapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val users = liveData {
        val response = apiService.getUsers()
        emit(response)
    }
}

package com.example.bookingapp.network

import com.example.bookingapp.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}

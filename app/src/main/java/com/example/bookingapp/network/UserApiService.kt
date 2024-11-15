package com.example.bookingapp.network

import com.example.bookingapp.model.LoginRequest
import com.example.bookingapp.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @GET("/Jezar20/MockData/refs/heads/main/users")
    suspend fun getUsers(): Response<List<User>>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<User>
}

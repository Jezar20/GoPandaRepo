package com.example.bookingapp.repository

import com.example.bookingapp.model.User
import retrofit2.Response

interface ImpRepository
{
    suspend fun getUser(): Response<List<User>>
}
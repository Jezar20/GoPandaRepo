package com.example.bookingapp.repository

import com.example.bookingapp.model.LoginRequest
import com.example.bookingapp.model.User
import com.example.bookingapp.network.UserApiService
import retrofit2.Response
import javax.inject.Inject


class UserRepository @Inject constructor(private val apiService: UserApiService): ImpRepository {
    override suspend fun getUser(): Response<List<User>> = apiService.getUsers()

    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)

}
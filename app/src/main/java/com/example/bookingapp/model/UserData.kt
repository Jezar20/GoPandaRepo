package com.example.bookingapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("userType")
    val userType: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("age")
    val age: Int?,

    @SerializedName("token")
    val token: String
)

data class LoginRequest(
    val phoneNumber: String,
    val googleAuthToken: String
)

data class SignupRequest(
    val phoneNumber: String,
    val age: Int,
    val googleAuthToken: String
)

data class GuestRequest(
    val title: String = "Guest User"
)

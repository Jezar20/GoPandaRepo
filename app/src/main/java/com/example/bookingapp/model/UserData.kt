package com.example.bookingapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("userType")
    val userType: String?,

    @SerializedName("userName")
    val userName: String?,

    @SerializedName("phoneNumber")
    val phoneNumber: String?,

    @SerializedName("age")
    val age: Int?,

    @SerializedName("token")
    val token: String?
)

@Serializable
data class Accounts(
    @SerialName("id")
    val userUuiD: String,
    @SerialName("created_at")
    val created_at: String,
    @SerialName("username")
    val userName: String,
    @SerialName("email")
    val email: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("password")
    val password: String,
    @SerialName("user_type")
    val user_type: String,
)

data class LoginRequest(
    val phoneNumber: String,
    val googleAuthToken: String
)

data class SignupRequest(
    val phoneNumber: String,
    val age: String,
    val googleAuthToken: String
)

data class GuestRequest(
    val title: String = "Guest User"
)

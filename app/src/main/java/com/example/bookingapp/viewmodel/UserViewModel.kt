package com.example.bookingapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.model.Accounts
import com.example.bookingapp.model.LoginRequest
import com.example.bookingapp.model.SignupRequest
import com.example.bookingapp.model.User
import com.example.bookingapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
//class UserViewModel @Inject constructor(private val userApiService: UserApiService) : ViewModel() {
class UserViewModel @Inject constructor(private val repository: Postgrest,): ViewModel()
{
//    val userPhoneNumber = MutableLiveData<List<String>>()
//
//    fun fetchUsers() {
//        viewModelScope.launch {
//            val response = userApiService.getUsers()
//            if (response.isSuccessful) {
//                response.body()?.let { users ->
//                    val phoneNumbers = users.map { it.phoneNumber }
//                    userPhoneNumber.value = phoneNumbers
//                    Log.i("TAG", " Here: $phoneNumbers")
//                }
//            }
//        }
//    }


    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    val _loginResponse = MutableLiveData<String>()
    val loginResponse: LiveData<String> get() = _loginResponse

//    init {
//        // Set default guest user
//        setGuestUser()
//    }
//
//    private fun setGuestUser() {
//        val guestUser = User(
//            userId = -1,
//            userType = "Guest",
//            userName = "",
//            phoneNumber = null,
//            age = null,
//            token = ""
//        )
//        _user.value = guestUser
//    }



    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = repository.from("Users").select().decodeList<Accounts>()

                result.forEach{
                    val email = MutableLiveData<List<String>>()
                }
            }catch (e: Exception){
                Log.i("TAG","$e")
            }

            /* if (response.isSuccessful) {
                 response.body()?.forEach {
                     orders.add(it.username)
                 }
                 orderTypes.value = orders
             } else {
                 Log.i("TAG", "Error: $response")
             }*/
        }
    }


    fun login(email: String) {

        viewModelScope.launch {
            try {
//                Log.i("DEBUG", "Attempting login with phone number: $phoneNumber")
                val response = repository.from("Users").select().decodeList<Accounts>()
                Log.i("TAG", "Success: $response is existing")
                if(response.isNotEmpty())
                {
                    val numberExists = response.any{it.email == email}
                    if(numberExists)
                    {
                        _loginResponse.postValue("Success")
                        Log.i("TAG", "Success: $email is existing")
                    }
                    else
                    {
                        _loginResponse.postValue("Failed")
                        Log.i("TAG", "$email is not existing")
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error during login", e)
                // Revert to guest user if an exception occurs

            }
        }
    }




    fun signup(phoneNumber: String, age: String, googleAuthToken: String) {
        val signupRequest = SignupRequest(phoneNumber, age, googleAuthToken)
        viewModelScope.launch {
//            val response = userRepository.createUser(signupRequest)
//            if (response.isSuccessful) {
//                _user.value = response.body()
//            } else {
//                _user.postValue(null)
//            }
        }
    }


    fun setUser(user: User?)
    {
        _user.value = user
    }

    fun resetState()
    {
        _user.value = null
    }

}


package com.example.bookingapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.Observer
import com.example.bookingapp.MainActivity
import com.example.bookingapp.ui.SideBarActivity
import com.example.bookingapp.R
import com.example.bookingapp.databinding.ActivityLoginBinding
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.viewmodel.UserViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.exceptions.RestException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()
    private var isLoginProcessed = false
    private var isGuest: Boolean = true

    val userPhoneNumber = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.fetchData()

//        binding.btnLogin.setOnClickListener {
//            val phoneNumber = binding.txtFieldPhoneNumber.text.toString()
//            userViewModel.login(phoneNumber)
//        }
//
//        userViewModel.user.observe(this, Observer { user ->
//
//                if (user != null) {
//                    if (user.phoneNumber == binding.txtFieldPhoneNumber.text.toString()) {
//                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, SideBarActivity::class.java).apply {
//                            putExtra("userName",user.userType)
//                            putExtra("isGuest",false)
//                        }
//                        startActivity(intent)
//                        finish()
//                    }
//                    userViewModel.resetState()
//                } else {
//                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
//                }
//
//        })

        binding.txtDescription4Login.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

//        binding.btnLogin.setOnClickListener{
//            val intent = Intent(this, SideBarActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnBack.setOnClickListener(){
            finish()
        }

        binding.btnLoginGoogle.setOnClickListener{
            GoogleSignInButton()
        }

        observeGoogleLoginResponse()
    }

    private fun observeGoogleLoginResponse() {
        userViewModel._loginResponse.observe(this@LoginActivity) { response ->
            Log.i("TAG","$response")
            when(response) {
                "Success" -> {
                    Toast.makeText(this, "Email is registered", Toast.LENGTH_LONG).show()
                    // Put here the intent to OTP
                    val user = userViewModel.user.value
                    val intent = Intent(this, SideBarActivity::class.java).apply {
                        putExtra("userName", user?.userType ?: "Unknown")
                        putExtra("userPhoneNumber", user?.phoneNumber)
                    }
                    startActivity(intent)
//                    val fragment = HomeFragment()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .addToBackStack(null)
//                        .commit()
                }

                "Failed"-> {
                    Toast.makeText(this, "Email is not yet registered", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                }
                else -> {
                    Toast.makeText(this, "Unexpected response", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun GoogleSignInButton() {
        val coroutineScope = CoroutineScope(Dispatchers.Main)


        val credentialManager = CredentialManager.create(this)

        // Generate a nonce and hash it with sha-256
        // Providing a nonce is optional but recommended
        val rawNonce = UUID.randomUUID()
            .toString() // Generate a random String. UUID should be sufficient, but can also be any other random string.
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce =
            digest.fold("") { str, it -> str + "%02x".format(it) } // Hashed nonce to be passed to Google sign-in


        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("30024932403-30jtp3828jt39k0ult1805sko4p095gv.apps.googleusercontent.com")

            .setNonce(hashedNonce) // Provide the nonce if you have one
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {

            try {

                val result = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity,
                )
                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(result.credential.data)

                val googleIdToken = googleIdTokenCredential.idToken


//
//                Log.i("TAG", "${googleIdTokenCredential.id}")
                userViewModel.login(googleIdTokenCredential.id)

                //viewModel.GoogleLogin("${googleIdTokenCredential.id}")

                /*   supabase.auth.signInWith(IDToken) {
                    idToken = googleIdToken
                    provider = Google
                    nonce = rawNonce
                }*/

                // Handle successful sign-in
            } catch (e: GetCredentialException) {
                Log.i("TAG", "Error: $e")
            } catch (e: GoogleIdTokenParsingException) {
                // Handle GoogleIdTokenParsingException thrown by `GoogleIdTokenCredential.createFrom()`
                Log.i("TAG", "Error: $e")
            } catch (e: RestException) {
                // Handle RestException thrown by Supabase
                Log.i("TAG", "Error: $e")
            } catch (e: Exception) {
                // Handle unknown exceptions
                Log.i("TAG", "Error: $e")
            }

        }

    }
}
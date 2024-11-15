package com.example.bookingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.example.bookingapp.R
import com.example.bookingapp.databinding.ActivitySidebarBinding
import com.example.bookingapp.databinding.NavHeaderBinding
import com.example.bookingapp.model.User
import com.example.bookingapp.ui.view.HomeFragment
import com.example.bookingapp.ui.view.LoginActivity
import com.example.bookingapp.ui.view.OrdersFragment
import com.example.bookingapp.ui.view.SettingsFragment
import com.example.bookingapp.ui.view.WalletFragment
import com.example.bookingapp.viewmodel.UserViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class SideBarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivitySidebarBinding
    private lateinit var headerBinding: NavHeaderBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySidebarBinding.inflate(layoutInflater)
        headerBinding = NavHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access the header binding
//        val headerView = binding.navView.getHeaderView(0)
//        headerBinding = NavHeaderBinding.bind(headerView)

        val userName = intent?.getStringExtra("userName")
        val phoneNumber = intent?.getStringExtra("userPhoneNumber")
        Log.i("TAG", "Received userName: $userName, userPhoneNumber: $phoneNumber")
        //val container = ArrayList<String>()

        userViewModel.user.observe(this){
            Log.i("TAG","$it")
            //container.addAll(listOf(it?.phoneNumber.toString()))
            if (it != null) { updateGuestOrUserName(it) }
        }
        updateGuestOrUserName(User(null,null,userName,phoneNumber,null,null))


//            when (userName) {
//                "Guest" -> {
//                    Log.i("DEBUG", "isGuest")
//                    headerBinding.txtGuestOrUserName.text = "Guest"
//                    headerBinding.txtUserPhoneNumber.text = ""
//                }
//                "User" -> {
//                    headerBinding.txtGuestOrUserName.text = container.toString()
//                }
//                else -> {
//                    Log.i("DEBUG", "Unknown user type")
//                    headerBinding.txtGuestOrUserName.text = "Unknown"
//                    headerBinding.txtUserPhoneNumber.text = ""
//                }
//            }
           // Log.i("DEBUG", "User LiveData updated: userID: ${user.userId}, userType: ${user.userType}, phoneNumber: ${user.phoneNumber}")


        //userViewModel.fetchUserData()

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inflate and add your custom layout to the toolbar
        val inflater = LayoutInflater.from(this)
        val customToolbar = inflater.inflate(R.layout.home_toolbar, toolbar, false)
        toolbar.addView(customToolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.open_nav,
            R.string.close_nav
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateGuestOrUserName(user: User?) {
        if (user != null) {
            Log.i("DEBUG", "Update UI with user: userType: ${user.userType}, userID: ${user.userId}, phoneNumber: ${user.phoneNumber}")
        } else {
            Log.i("DEBUG", "Update UI with user null")
        }


        when (user?.userType) {
            "Guest" -> {
                Log.i("DEBUG", "isGuest")
                headerBinding.txtGuestOrUserName.text = "Guest"
                headerBinding.txtUserPhoneNumber.text = ""
            }
            "User" -> {
                Log.i("DEBUG", "isUser")
                headerBinding.txtGuestOrUserName.text = "UserID: " + user.userId.toString()
                headerBinding.txtUserPhoneNumber.text = user.phoneNumber ?: ""
            }
            else -> {
                Log.i("DEBUG", "Unknown user type")
                headerBinding.txtGuestOrUserName.text = "Unknown"
                headerBinding.txtUserPhoneNumber.text = ""
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment()).commit()
            R.id.nav_orders -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OrdersFragment()).commit()
            R.id.nav_wallet -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WalletFragment()).commit()
            R.id.nav_login_signup -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                exitProcess(0)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressedDispatcher.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
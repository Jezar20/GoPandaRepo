package com.example.bookingapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageView
import com.example.bookingapp.R

class AddCouponFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_coupon, container, false)

        val btnBack: AppCompatImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back press. For example, pop the fragment back stack
                requireActivity().supportFragmentManager.popBackStack()
            }
        })

        val txtDone = view.findViewById<TextView>(R.id.txtDone)
        txtDone.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back press. For example, pop the fragment back stack
                requireActivity().supportFragmentManager.popBackStack()
            }
        })

        return view
    }

}
package com.example.bookingapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.bookingapp.R

class SearchOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_orders, container, false)

        // Hide toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        val btnBackSignUp: AppCompatImageView = view.findViewById(R.id.btnBack)
        btnBackSignUp.setOnClickListener {
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

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Show toolbar again when leaving fragment
//        (activity as? AppCompatActivity)?.supportActionBar?.show()
//    }

}
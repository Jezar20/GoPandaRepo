package com.example.bookingapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.bookingapp.R

class WalletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wallet, container, false)
        // Hide toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        val btnBackWallet: AppCompatImageView = view.findViewById(R.id.btnBack)
        btnBackWallet.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Show toolbar again when leaving fragment
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

}
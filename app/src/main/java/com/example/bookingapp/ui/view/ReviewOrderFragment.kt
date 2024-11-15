package com.example.bookingapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageView
import com.example.bookingapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class ReviewOrderFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_review_order, container, false)

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

        val crdCashOrWallet = view.findViewById<MaterialCardView>(R.id.crdViewCashOrWallet)
        crdCashOrWallet.setOnClickListener{
            val reviewOrderPaymentMethod = ReviewOrderPaymentMethodFragment()
                reviewOrderPaymentMethod.show(parentFragmentManager, reviewOrderPaymentMethod.tag)
        }

        val crdViewCoupon = view.findViewById<MaterialCardView>(R.id.crdViewCoupon)
        crdViewCoupon.setOnClickListener{
            val addCouponFragment = requireActivity().supportFragmentManager.beginTransaction()
            addCouponFragment.replace(R.id.fragment_container, AddCouponFragment())
            addCouponFragment.addToBackStack(null)
            addCouponFragment.commit()
        }

        val btnReviewOrder = view.findViewById<MaterialButton>(R.id.btnReviewOrder)
        btnReviewOrder.setOnClickListener {
            val placeOrderFragment = PlaceOrderFragment()
            placeOrderFragment.show(parentFragmentManager, placeOrderFragment.tag)
        }


        // Inflate the layout for this fragment
        return view
    }

}
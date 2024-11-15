package com.example.bookingapp.ui.view

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.bookingapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView

class ReviewOrderPaymentMethodFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_review_order_payment_method, container, false)

        val crdViewWallet = view.findViewById<MaterialCardView>(R.id.crdViewPaymentWallet)
        val crdViewCashSender = view.findViewById<MaterialCardView>(R.id.crdViewPaymentCashSender)
        val crdViewCashRecipient = view.findViewById<MaterialCardView>(R.id.crdViewPaymentCashRecipient)
        val bottomSheet: View = view.findViewById(R.id.bottom_sheet)
        val behavior = BottomSheetBehavior.from(bottomSheet)

        // Helper function to deselect all card views
        fun deselectAll() {
            crdViewWallet.isChecked = false
            crdViewWallet.strokeColor = Color.TRANSPARENT
            crdViewWallet.strokeWidth = 0

            crdViewCashSender.isChecked = false
            crdViewCashSender.strokeColor = Color.TRANSPARENT
            crdViewCashSender.strokeWidth = 0

            crdViewCashRecipient.isChecked = false
            crdViewCashRecipient.strokeColor = Color.TRANSPARENT
            crdViewCashRecipient.strokeWidth = 0
        }

        crdViewWallet.setOnClickListener {
            deselectAll()
            crdViewWallet.isChecked = true
            crdViewWallet.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
            crdViewWallet.strokeWidth = 4 // adjust stroke width as needed
            dismissBottomSheetWithDelay() // Dismiss the bottom sheet dialog with delay
        }

        crdViewCashSender.setOnClickListener {
            deselectAll()
            crdViewCashSender.isChecked = true
            crdViewCashSender.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
            crdViewCashSender.strokeWidth = 4 // adjust stroke width as needed
            dismissBottomSheetWithDelay() // Dismiss the bottom sheet dialog with delay
        }

        crdViewCashRecipient.setOnClickListener {
            deselectAll()
            crdViewCashRecipient.isChecked = true
            crdViewCashRecipient.strokeColor = ContextCompat.getColor(requireContext(),
                R.color.yellow
            )
            crdViewCashRecipient.strokeWidth = 4 // adjust stroke width as needed
            dismissBottomSheetWithDelay() // Dismiss the bottom sheet dialog with delay
        }

        // Make the bottom sheet expanded when created
        bottomSheet.post {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return view
    }

    private fun dismissBottomSheetWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            val bottomSheetDialog = dialog as? BottomSheetDialog
            bottomSheetDialog?.dismiss()
        }, 300) // Adjust the delay time as needed (300 milliseconds in this case)
    }

}
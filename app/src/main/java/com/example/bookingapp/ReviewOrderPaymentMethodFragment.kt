package com.example.bookingapp

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewOrderPaymentMethodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewOrderPaymentMethodFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
            crdViewCashRecipient.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewOrderPaymentMethod.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewOrderPaymentMethodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
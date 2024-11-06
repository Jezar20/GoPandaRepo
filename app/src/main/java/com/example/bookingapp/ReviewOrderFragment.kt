package com.example.bookingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewOrderFragment : BottomSheetDialogFragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddMoreDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
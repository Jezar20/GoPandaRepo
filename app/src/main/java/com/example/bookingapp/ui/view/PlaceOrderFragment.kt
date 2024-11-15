package com.example.bookingapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookingapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlaceOrderFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_place_order, container, false)

        val bottomSheet: View = view.findViewById(R.id.bottom_sheet)
        val behavior = BottomSheetBehavior.from(bottomSheet)

        // Make the bottom sheet expanded when created
        bottomSheet.post {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return view
    }

}
package com.example.bookingapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookingapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SelectedServiceFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_selected_service, container, false)

// STATIC CUSTOM BOTTOM SHEET DIALOG

//        val mtrlCrdPriority = view.findViewById<MaterialCardView>(R.id.mtrlCrdPriority)
//        val mtrlCrdRegular = view.findViewById<MaterialCardView>(R.id.mtrlCrdRegular)
//
//        // Helper function to deselect all card views
//        fun deselectAll() {
//            mtrlCrdPriority.isChecked = false
//            mtrlCrdPriority.strokeColor = Color.TRANSPARENT
//            mtrlCrdPriority.strokeWidth = 0
//
//            mtrlCrdRegular.isChecked = false
//            mtrlCrdRegular.strokeColor = Color.TRANSPARENT
//            mtrlCrdRegular.strokeWidth = 0
//        }
//
//        mtrlCrdPriority.setOnClickListener {
//            deselectAll()
//            mtrlCrdPriority.isChecked = true
//            mtrlCrdPriority.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
//            mtrlCrdPriority.strokeWidth = 4 // adjust stroke width as needed
//        }
//
//        mtrlCrdRegular.setOnClickListener {
//            deselectAll()
//            mtrlCrdRegular.isChecked = true
//            mtrlCrdRegular.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
//            mtrlCrdRegular.strokeWidth = 4 // adjust stroke width as needed
//        }
//
//        // Select the "Regular" card by default
//        mtrlCrdRegular.performClick()
//
//        val btnNext = view.findViewById<MaterialButton>(R.id.btnNext)
//        btnNext.setOnClickListener {
//            // Hide toolbar
//            (activity as? AppCompatActivity)?.supportActionBar?.hide()
//
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, ReviewOrderFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//
//        }


// BOTTOM SHEET DIALOG

//        val bottomSheet: View = view.findViewById(R.id.bottom_sheet)
//        val behavior = BottomSheetBehavior.from(bottomSheet)
//        behavior.isHideable = true
//        behavior.state = BottomSheetBehavior.STATE_HIDDEN
//
//        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheet.post {
//                        bottomSheet.layoutParams.height = 0
//                        bottomSheet.visibility = View.GONE
//                        bottomSheet.requestLayout()
//                        dismissBottomSheet() // Dismiss the bottom sheet dialog
//                    }
//                } else {
//                    bottomSheet.post {
//                        bottomSheet.visibility = View.VISIBLE
//                        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//                        bottomSheet.requestLayout()
//                    }
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // Not needed for this use case
//            }
//        })
//
//        // Example of showing the bottom sheet
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//        val btnNext = view.findViewById<MaterialButton>(R.id.btnNext)
//        btnNext.setOnClickListener {
//            // Hide toolbar
//            (activity as? AppCompatActivity)?.supportActionBar?.hide()
//
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, ReviewOrderFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//
//            // Hide bottom sheet
//            behavior.state = BottomSheetBehavior.STATE_HIDDEN
//            bottomSheet.visibility = View.GONE
//            bottomSheet.layoutParams.height = 0
//            bottomSheet.requestLayout()
//            dismissBottomSheet() // Dismiss the bottom sheet dialog
//        }

        return view
    }

//    private fun dismissBottomSheet() {
//        val bottomSheetDialog = dialog as? BottomSheetDialog
//        bottomSheetDialog?.dismiss()
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
//        dialog.window?.setDimAmount(0f) // Set dim amount to 0 to avoid background dimming
//        return dialog
//    }

}
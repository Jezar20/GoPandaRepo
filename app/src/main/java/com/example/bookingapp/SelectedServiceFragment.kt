package com.example.bookingapp

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedServiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedServiceFragment : BottomSheetDialogFragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectedService.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                SelectedServiceFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
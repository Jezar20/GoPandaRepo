package com.example.bookingapp

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Show toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.show()

        // Set up the first MaterialCardView
        val card = view.findViewById<MaterialCardView>(R.id.crdViewVehicles)
        val recyclerView = view.findViewById<RecyclerView>(R.id.nested_rv)
        val textView = view.findViewById<TextView>(R.id.txtAdditionalServices)
        val scrollView = view.findViewById<ScrollView>(R.id.scrollView)
        val staticBottomSheet = view.findViewById<View>(R.id.staticBottomSheet)

        // Dummy data for RecyclerView
        val serviceList = listOf(
            Service("Buy for me (Up to ₱2,000)", "₱50.00"),
            Service("Extra waiting time (queueing service)", "₱70.00"),
            Service("Thermal bag", "₱0.00"),
            Service("Cash-on-delivery (Up to ₱2,000)", "₱30.00")
        )

        // Set up the RecyclerView adapter and layout manager
        val adapter = MyAdapter(serviceList, parentFragmentManager)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        card.setOnClickListener {
            card.isChecked = !card.isChecked
            recyclerView.visibility = if (card.isChecked) View.VISIBLE else View.GONE
            textView.visibility = if (card.isChecked) View.VISIBLE else View.GONE

            val strokeColor = if (card.isChecked) {
                ContextCompat.getColor(requireContext(), R.color.yellow)
            } else {
                Color.TRANSPARENT
            }
            card.strokeColor = strokeColor
            card.strokeWidth = if (card.isChecked) 4 else 0 // adjust stroke width as needed

            // Update checkmark color only if you have a custom drawable
            card.setCheckedIconTint(ContextCompat.getColorStateList(requireContext(), R.color.yellow))

            if (!card.isChecked) {
                (recyclerView.adapter as MyAdapter).uncheckAllInnerCards()
            }

            // Handle expansion and collapse
            val params = card.layoutParams
            val targetHeight = if (card.isChecked) expandedHeight else ViewGroup.LayoutParams.WRAP_CONTENT
            val valueAnimator = ValueAnimator.ofInt(card.height, targetHeight).apply {
                duration = 200 // Animation duration
                addUpdateListener { animation ->
                    params.height = animation.animatedValue as Int
                    card.layoutParams = params
                }
                doOnEnd {
                    if (card.isChecked) {
                        scrollView.post { scrollView.smoothScrollTo(0, staticBottomSheet.top) }
                    }
                }
            }
            valueAnimator.start()

            // Show the bottom sheet only when the card is selected (BOTTOM SHEET DIALOG)
//            if (card.isChecked) {
//                val bottomSheet = SelectedServiceFragment()
//                bottomSheet.show(parentFragmentManager, bottomSheet.tag)
//            }

            // Update the position of the static bottom sheet
            val layoutParams = staticBottomSheet.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.topToBottom = card.id
            staticBottomSheet.layoutParams = layoutParams

            // Show the bottom sheet only when the card is selected (CUSTOM BOTTOM SHEET DIALOG)
            if (card.isChecked) {
                staticBottomSheet.visibility = View.VISIBLE
            } else {
                staticBottomSheet.visibility = View.GONE
            }

        }

        val mtrlCrdPriority = view.findViewById<MaterialCardView>(R.id.mtrlCrdPriority)
        val mtrlCrdRegular = view.findViewById<MaterialCardView>(R.id.mtrlCrdRegular)

        // Helper function to deselect all card views
        fun deselectAll() {
            mtrlCrdPriority.isChecked = false
            mtrlCrdPriority.strokeColor = Color.TRANSPARENT
            mtrlCrdPriority.strokeWidth = 0

            mtrlCrdRegular.isChecked = false
            mtrlCrdRegular.strokeColor = Color.TRANSPARENT
            mtrlCrdRegular.strokeWidth = 0
        }

        mtrlCrdPriority.setOnClickListener {
            deselectAll()
            mtrlCrdPriority.isChecked = true
            mtrlCrdPriority.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
            mtrlCrdPriority.strokeWidth = 4 // adjust stroke width as needed
        }

        mtrlCrdRegular.setOnClickListener {
            deselectAll()
            mtrlCrdRegular.isChecked = true
            mtrlCrdRegular.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
            mtrlCrdRegular.strokeWidth = 4 // adjust stroke width as needed
        }

        // Select the "Regular" card by default
        mtrlCrdRegular.performClick()

        val btnNext = view.findViewById<MaterialButton>(R.id.btnNext)
        btnNext.setOnClickListener {
            // Hide toolbar
            (activity as? AppCompatActivity)?.supportActionBar?.hide()

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ReviewOrderFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }

        // Set up the second MaterialCardView
        val card2 = view.findViewById<MaterialCardView>(R.id.crdViewVehicleTypes)
        card2.setOnClickListener {
            // Navigate to the activity_available_vehicles.xml
            val intent = Intent(context, AvailableVehiclesActivity::class.java)
            startActivity(intent)
        }

        val txtPickUp = view.findViewById<TextView>(R.id.txtPickUp)
        txtPickUp.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SearchLocationFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        val txtDropOff = view.findViewById<TextView>(R.id.txtDropOff)
        txtDropOff.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SearchLocationFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

//        val btnAddStop = view.findViewById<TextView>(R.id.btnAddStop)
//        btnAddStop.setOnClickListener {
//            Toast.makeText(activity, "Add Stop", Toast.LENGTH_SHORT).show()
//        }

        return view
    }

    private fun showSelectedServiceFragment()
    {
        val selectedServiceFragment = SelectedServiceFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, selectedServiceFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Variables to track expanded state and dimensions
    private var expandedHeight = 1600


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val crdViewVehicles: MaterialCardView = view.findViewById(R.id.crdViewVehicles)
//        val crdViewDetails1: MaterialCardView = view.findViewById(R.id.crdViewDetails1)
//        val crdViewDetails2: MaterialCardView = view.findViewById(R.id.crdViewDetails2)
//        val crdViewDetails3: MaterialCardView = view.findViewById(R.id.crdViewDetails3)
//
//        crdViewVehicles.setOnClickListener {
//            val isVisible = crdViewDetails1.visibility == View.VISIBLE
//            val newVisibility = if (isVisible) View.GONE else View.VISIBLE
//            crdViewDetails1.visibility = newVisibility
//            crdViewDetails2.visibility = newVisibility
//            crdViewDetails3.visibility = newVisibility
//        }
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        // Find your MaterialCardView
//        val card = view.findViewById<MaterialCardView>(R.id.crdViewVehicles)
//
//        // Set the OnClickListener
//        card.setOnClickListener {
//            card.isChecked = !card.isChecked
//            if (card.isChecked) {
//                card.strokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
//                card.strokeWidth = 4 // Adjust stroke width as needed
//                card.setCardForegroundColor(ContextCompat.getColorStateList(requireContext(), R.color.white)) // Set foreground color to white when checked
//                card.setCheckedIconTint(ContextCompat.getColorStateList(requireContext(), R.color.yellow)) // Apply checkmark tint
//            } else {
//                card.strokeColor = Color.TRANSPARENT
//                card.strokeWidth = 0
//                card.setCardForegroundColor(ContextCompat.getColorStateList(requireContext(), R.color.white)) // Reset foreground color
//                card.setCheckedIconTint(ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)) // Remove checkmark tint
//            }
//        }
//
//        return view
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
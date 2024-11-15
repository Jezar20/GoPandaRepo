package com.example.bookingapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentContainerView
import com.example.bookingapp.R
import com.google.android.material.search.SearchBar
import com.google.android.material.tabs.TabLayout

class OrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        // Hide toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val fragmentContainerView = view.findViewById<FragmentContainerView>(R.id.fragmentContainerView)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment: Fragment = when (tab.position) {
                    0 -> OrdersOngoingFragment()
                    1 -> OrdersCompletedFragment()
                    else -> OrdersCancelledFragment()
                }
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Load the initial fragment for "Ongoing" tab
        if (savedInstanceState == null) {
            tabLayout.selectTab(tabLayout.getTabAt(0))
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, OrdersOngoingFragment())
                .commit()
        }

        val btnBack: AppCompatImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        val searchBar = view.findViewById<SearchBar>(R.id.searchBarOrders)
        searchBar.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SearchOrdersFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Show toolbar again when leaving fragment
//        (activity as? AppCompatActivity)?.supportActionBar?.show()
//    }

}
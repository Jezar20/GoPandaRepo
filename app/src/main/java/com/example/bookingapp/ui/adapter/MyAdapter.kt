package com.example.bookingapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.R
import com.example.bookingapp.model.Service
import com.google.android.material.card.MaterialCardView

class MyAdapter(private val serviceList: List<Service>, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // List to maintain the checked state of each item
    private val checkedStates = MutableList(serviceList.size) { false }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtServiceDescription: TextView = itemView.findViewById(R.id.txtServiceDescription)
        val txtServicePrice: TextView = itemView.findViewById(R.id.txtServicePrice)
        val innerCardView: MaterialCardView = itemView.findViewById(R.id.mtrlCrdServices)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val service = serviceList[position]
        holder.txtServiceDescription.text = service.description
        holder.txtServicePrice.text = service.price

        // Update the checked state of innerCardView based on checkedStates
        holder.innerCardView.isChecked = checkedStates[position]
        val strokeColor = if (checkedStates[position]) {
            ContextCompat.getColor(holder.itemView.context, R.color.yellow)
        } else {
            Color.TRANSPARENT
        }
        holder.innerCardView.strokeColor = strokeColor
        holder.innerCardView.strokeWidth = if (checkedStates[position]) 4 else 0

        // Handle inner CardView click without showing the bottom sheet
        holder.innerCardView.setOnClickListener {
            checkedStates[position] = !checkedStates[position]
            notifyItemChanged(position)

//            Show sheet when innercard is clicked
//            if (checkedStates[position]) {
//                // Show the bottom sheet
//                val bottomSheet = SelectedServiceFragment()
//                bottomSheet.show(fragmentManager, bottomSheet.tag)
//            }
        }
    }

    override fun getItemCount(): Int = serviceList.size

    // Method to uncheck all inner cards
    fun uncheckAllInnerCards() {
        for (i in checkedStates.indices) {
            checkedStates[i] = false
        }
        notifyDataSetChanged()
    }
}



//class MyAdapter(private val serviceList: List<Service>) :
//    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val txtServiceDescription: TextView = itemView.findViewById(R.id.txtServiceDescription)
//        val txtServicePrice: TextView = itemView.findViewById(R.id.txtServicePrice)
//        val innerCardView: MaterialCardView = itemView.findViewById(R.id.mtrlCrdServices)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.child_item, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val service = serviceList[position]
//        holder.txtServiceDescription.text = service.description
//        holder.txtServicePrice.text = service.price
//
//        // Handle inner CardView click
//        holder.innerCardView.setOnClickListener {
//            holder.innerCardView.isChecked = !holder.innerCardView.isChecked
//            val strokeColor = if (holder.innerCardView.isChecked) {
//                ContextCompat.getColor(holder.itemView.context, R.color.yellow)
//            } else {
//                Color.TRANSPARENT
//            }
//            holder.innerCardView.strokeColor = strokeColor
//            holder.innerCardView.strokeWidth = if (holder.innerCardView.isChecked) 4 else 0
//        }
//    }
//
//    override fun getItemCount(): Int = serviceList.size
//
//    // Method to uncheck all items
//    fun resetSelection() {
//        for (i in 0 until itemCount) {
//            notifyItemChanged(i, false)
//        }
//    }
//}

//class MyAdapter(private val serviceList: List<Service>) :
//    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//
//    private val selectedPositions = mutableSetOf<Int>()
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val txtServiceDescription: TextView = itemView.findViewById(R.id.txtServiceDescription)
//        val txtServicePrice: TextView = itemView.findViewById(R.id.txtServicePrice)
//        val innerCardView: MaterialCardView = itemView.findViewById(R.id.mtrlCrdServices)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.child_item, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val service = serviceList[position]
//        holder.txtServiceDescription.text = service.description
//        holder.txtServicePrice.text = service.price
//
//        val isSelected = selectedPositions.contains(position)
//        holder.innerCardView.isChecked = isSelected
//        val strokeColor = if (isSelected) {
//            ContextCompat.getColor(holder.itemView.context, R.color.yellow)
//        } else {
//            Color.TRANSPARENT
//        }
//        holder.innerCardView.strokeColor = strokeColor
//        holder.innerCardView.strokeWidth = if (isSelected) 4 else 0
//
//        // Handle inner CardView click
//        holder.innerCardView.setOnClickListener {
//            if (selectedPositions.contains(position)) {
//                selectedPositions.remove(position)
//            } else {
//                selectedPositions.add(position)
//            }
//            notifyItemChanged(position)
//        }
//    }
//
//    override fun getItemCount(): Int = serviceList.size
//
//    // Method to uncheck all items
//    fun resetSelection() {
//        selectedPositions.clear()
//        notifyDataSetChanged()
//    }
//}


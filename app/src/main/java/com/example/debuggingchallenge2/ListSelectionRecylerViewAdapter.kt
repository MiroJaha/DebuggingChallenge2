package com.example.debuggingchallenge2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val stateAndCapitals: ArrayList<ArrayList<Any>>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = stateAndCapitals[position][0].toString()
        holder.listTitle.text = stateAndCapitals[position][2].toString()
        holder.listCountries.text = stateAndCapitals[position][1].toString()

        holder.mainLayout.setOnClickListener{

        }
    }


    override fun getItemCount(): Int {
        return stateAndCapitals.size
    }

}
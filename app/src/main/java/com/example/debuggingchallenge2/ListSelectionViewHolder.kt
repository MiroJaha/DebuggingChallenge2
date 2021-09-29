package com.example.debuggingchallenge2

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mainLayout: LinearLayout = itemView.findViewById(R.id.mailL)
    val listPosition: TextView = itemView.findViewById(R.id.numberId)
    val listTitle: TextView = itemView.findViewById(R.id.itemString)
    val listCountries: TextView = itemView.findViewById(R.id.itemNumber)
}
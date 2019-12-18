package com.monese.rocket.view.adapters.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monese.rocket.R

class CoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val coreSerial: TextView = view.findViewById(R.id.core_serial)
    val flightNumber: TextView = view.findViewById(R.id.flight_number)
    val block: TextView = view.findViewById(R.id.block)
    val reused: TextView = view.findViewById(R.id.reused)
}
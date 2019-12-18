package com.monese.rocket.view.adapters.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monese.rocket.R

class PayloadViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val payloadId: TextView = view.findViewById(R.id.payload_id)
    val type: TextView = view.findViewById(R.id.type)
    val mass: TextView = view.findViewById(R.id.mass)
    val reused: TextView = view.findViewById(R.id.reused)
}
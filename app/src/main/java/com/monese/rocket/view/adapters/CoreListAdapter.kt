package com.monese.rocket.view.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monese.rocket.R
import com.monese.rocket.model.models.Core
import com.monese.rocket.view.adapters.holders.CoreViewHolder

class CoreListAdapter(private val cores: List<Core>) :
    RecyclerView.Adapter<CoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder {
        return CoreViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.core_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cores.size + 1
    }

    override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {
        if (position == 0) {
            holder.coreSerial.text = holder.itemView.context.getString(R.string.serial)
            holder.flightNumber.text = holder.itemView.context.getString(R.string.flight)
            holder.block.text = holder.itemView.context.getString(R.string.block)
            holder.reused.text = holder.itemView.context.getString(R.string.reused)
        } else {
            val core = cores[position - 1]
            if (!TextUtils.isEmpty(core.coreSerial))
                holder.coreSerial.text = core.coreSerial
            else holder.coreSerial.text = holder.itemView.context.getString(R.string.unavailable)
            if (core.flight != null)
                holder.flightNumber.text = core.flight.toString()
            else holder.flightNumber.text = holder.itemView.context.getString(R.string.unavailable)
            if (core.block != null)
                holder.block.text = core.block.toString()
            else holder.block.text = holder.itemView.context.getString(R.string.unavailable)
            if (core.reused != null) {
                if (core.reused)
                    holder.reused.text = holder.itemView.context.getString(R.string.yes)
                else holder.reused.text = holder.itemView.context.getString(R.string.no)
            } else holder.reused.text = holder.itemView.context.getString(R.string.unavailable)
        }
    }
}
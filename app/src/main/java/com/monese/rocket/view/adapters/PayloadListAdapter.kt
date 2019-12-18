package com.monese.rocket.view.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monese.rocket.R
import com.monese.rocket.model.models.Payload
import com.monese.rocket.view.adapters.holders.PayloadViewHolder

class PayloadListAdapter(private val payloads: List<Payload>) :
    RecyclerView.Adapter<PayloadViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayloadViewHolder {
        return PayloadViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.payload_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return payloads.size + 1
    }

    override fun onBindViewHolder(holder: PayloadViewHolder, position: Int) {
        if (position == 0) {
            holder.payloadId.text = holder.itemView.context.getString(R.string.payload_id)
            holder.type.text = holder.itemView.context.getString(R.string.payload_type)
            holder.mass.text = holder.itemView.context.getString(R.string.mass)
            holder.reused.text = holder.itemView.context.getString(R.string.reused)
        } else {
            val payload=payloads[position-1]
            if (!TextUtils.isEmpty(payload.payloadId))
                holder.payloadId.text = payload.payloadId
            else holder.payloadId.text = holder.itemView.context.getString(R.string.unavailable)
            if (!TextUtils.isEmpty(payload.payloadType))
                holder.type.text = payload.payloadType
            else holder.type.text = holder.itemView.context.getString(R.string.unavailable)
            var mass:String
            mass = if (payload.payloadMassKg!=null)
                payload.payloadMassKg.toString()
            else holder.itemView.context.getString(R.string.unavailable)
            mass += if(payload.payloadMassLbs!=null){
                " - "+payload.payloadMassLbs
            }else " - "+holder.itemView.context.getString(R.string.unavailable)
            holder.mass.text=mass
            if (payload.reused != null) {
                if (payload.reused)
                    holder.reused.text = holder.itemView.context.getString(R.string.yes)
                else holder.reused.text = holder.itemView.context.getString(R.string.no)
            } else holder.reused.text = holder.itemView.context.getString(R.string.unavailable)
        }
    }
}
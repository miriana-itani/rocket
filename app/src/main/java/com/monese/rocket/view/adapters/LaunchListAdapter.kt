package com.monese.rocket.view.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monese.rocket.R
import com.monese.rocket.model.models.Launch
import com.monese.rocket.utils.CompareObjects
import com.monese.rocket.view.fragments.MainFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class LaunchListAdapter(private val context: Context) :
    ListAdapter<Launch, RecyclerView.ViewHolder>(LaunchDiffCallback()) {
    private val format = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LaunchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.launch_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val launch = getItem(position)
        val launchHolder = holder as LaunchViewHolder
        if (!TextUtils.isEmpty(launch.missionName))
            launchHolder.launchName.text = launch.missionName
        if (launch.missionId != null && launch.missionId!!.isNotEmpty()) {
            launchHolder.launchMissionId.visibility = View.VISIBLE
            launchHolder.launchMissionId.text = launch.missionId!!.joinToString {
                it
            }
        } else launchHolder.launchMissionId.visibility = View.INVISIBLE
        if (launch.lastDateUpdate != null)
            launchHolder.launchDate.text = format.format(launch.lastDateUpdate!!)
        if (launch.links != null && !TextUtils.isEmpty(launch.links!!.missionPatchSmall))
            Glide.with(launchHolder.itemView).load(launch.links!!.missionPatchSmall).placeholder(R.drawable.placeholder)
                .centerInside().into(launchHolder.launchImage)
        else Glide.with(launchHolder.itemView).load(R.drawable.placeholder).centerInside().into(
            launchHolder.launchImage
        )
        if (launch.tbd != null && launch.tbd!!)
            launchHolder.arrow.setColorFilter(
                ContextCompat.getColor(context, R.color.green)
            )
        else launchHolder.arrow.setColorFilter(
            ContextCompat.getColor(context, R.color.colorAccent)
        )
        launchHolder.itemView.setOnClickListener {
            Navigation.findNavController(launchHolder.itemView)
                .navigate(MainFragmentDirections.detailsPage(launch))
        }
    }

    class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val launchName: TextView = view.findViewById(R.id.launch_name)
        val launchMissionId: TextView = view.findViewById(R.id.launch_mission_id)
        val launchImage: AppCompatImageView = view.findViewById(R.id.launch_image)
        val launchDate: TextView = view.findViewById(R.id.launch_date)
        val arrow: AppCompatImageView = view.findViewById(R.id.arrow)
    }
}

private class LaunchDiffCallback : DiffUtil.ItemCallback<Launch>() {


    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.flightNumber == newItem.flightNumber
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return CompareObjects.compare(oldItem, newItem)
    }

}

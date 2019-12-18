package com.monese.rocket.view.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monese.rocket.R
import com.monese.rocket.model.models.Launch
import com.monese.rocket.view.adapters.CoreListAdapter
import com.monese.rocket.view.adapters.PayloadListAdapter

class LaunchDetailsPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_launch_details, container, false)
        val launch = arguments?.get("launch") as Launch
        val launchName = view.findViewById<TextView>(R.id.launch_name)
        if (!TextUtils.isEmpty(launch.missionName))
            launchName.text = launch.missionName
        val launchImage = view.findViewById<AppCompatImageView>(R.id.launch_image)
        val videoPlayer = view.findViewById<AppCompatImageView>(R.id.video_player)
        val rocketInfo = view.findViewById<TextView>(R.id.rocket_info)
        val cores = view.findViewById<RecyclerView>(R.id.cores)
        val coresLabel = view.findViewById<TextView>(R.id.cores_label)
        val payloads = view.findViewById<RecyclerView>(R.id.payloads)
        val payloadLabel = view.findViewById<TextView>(R.id.payloads_label)
        val dividerItemDecoration = DividerItemDecoration(
            cores.context,
            RecyclerView.VERTICAL
        )
        cores.addItemDecoration(dividerItemDecoration)
        payloads.addItemDecoration(dividerItemDecoration)
        if (launch.links != null) {
            if (!TextUtils.isEmpty(launch.links!!.missionPatchSmall))
                Glide.with(context!!).load(launch.links!!.missionPatchSmall).placeholder(R.drawable.placeholder).centerInside()
                    .into(launchImage)
            if (TextUtils.isEmpty(launch.links!!.youtubeId))
                videoPlayer.visibility = View.GONE
            else {
                videoPlayer.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("videoId", launch.links!!.youtubeId)
                    Navigation.findNavController(view).navigate(R.id.videoPlayerActivity, bundle)
                }
            }
        } else {
            videoPlayer.visibility = View.GONE
            Glide.with(context!!).load(R.drawable.placeholder).centerInside().into(launchImage)
        }

        if (launch.rocket != null) {
            var info = ""
            if (!TextUtils.isEmpty(launch.rocket!!.rocketName))
                info = "Rocket Name: " + launch.rocket!!.rocketName
            if (!TextUtils.isEmpty(launch.rocket!!.rocketType))
                info += " - Rocket Type: " + launch.rocket!!.rocketType
            rocketInfo.text = info
            if (launch.rocket!!.firstStage != null && !launch.rocket!!.firstStage!!.cores.isNullOrEmpty()) {
                cores.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
                cores.adapter = CoreListAdapter(launch.rocket!!.firstStage!!.cores!!)
            } else {
                coresLabel.visibility = View.GONE
                cores.visibility = View.GONE
            }
            if (launch.rocket!!.secondStage != null && !launch.rocket!!.secondStage!!.payloads.isNullOrEmpty()) {
                payloads.layoutManager =
                    LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
                payloads.adapter = PayloadListAdapter(launch.rocket!!.secondStage!!.payloads!!)
            } else {
                payloadLabel.visibility = View.GONE
                payloads.visibility = View.GONE
            }
        }
        return view
    }
}
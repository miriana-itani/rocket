package com.monese.rocket.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.monese.rocket.R
import com.monese.rocket.di.ViewModelFactory
import com.monese.rocket.model.models.Launch
import com.monese.rocket.view.adapters.LaunchListAdapter
import com.monese.rocket.vm.MainActivityViewModel
import com.monese.rocket.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var isChecked = false
    private var launches = mutableListOf<Launch>()
    private var filtered = mutableListOf<Launch>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_page, container, false)
        val launchesList = view.findViewById<RecyclerView>(R.id.launches_list)
        launchesList.layoutManager =
            LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            launchesList.context,
            RecyclerView.VERTICAL
        )
        launchesList.addItemDecoration(dividerItemDecoration)
        val filter = view.findViewById<FloatingActionButton>(R.id.filter)
        val launchesListAdapter = LaunchListAdapter(context!!)
        launchesList.adapter = launchesListAdapter
        if (isChecked) {
            filter.setImageResource(R.drawable.clear_filters)
        } else {
            filter.setImageResource(R.drawable.filter)
        }
        filter.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                filter.setImageResource(R.drawable.clear_filters)
                launchesListAdapter.submitList(filtered)
            } else {
                launchesListAdapter.submitList(launches)
                filter.setImageResource(R.drawable.filter)
            }
        }
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        swipe.setOnRefreshListener {
            mainActivityViewModel.setRefresh(true)
        }
        val progress = view.findViewById<LottieAnimationView>(R.id.progress)
        mainActivityViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)
        mainActivityViewModel.setRefresh(false)
        mainActivityViewModel.results.observe(this, Observer { launches ->
            when (launches.status) {
                Status.LOADING -> {
                    progress.playAnimation()
                    progress.visibility = View.VISIBLE
                    launchesList.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    launchesList.visibility = View.VISIBLE
                    if (launches.data != null && launches.data.isNotEmpty()) {
                        this.launches = launches.data
                        filtered =
                            this.launches.filter { filter -> filter.tbd != null && filter.tbd!! }
                                .toMutableList()
                        swipe.isRefreshing = false
                        progress.pauseAnimation()
                        progress.visibility = View.GONE
                        if (isChecked) launchesListAdapter.submitList(filtered)
                        else launchesListAdapter.submitList(launches.data)
                    }
                }
                Status.ERROR -> {
                    swipe.isRefreshing = false
                    launchesList.visibility = View.VISIBLE
                    progress.pauseAnimation()
                    progress.visibility = View.GONE
                    Toast.makeText(
                        context!!,
                        "Something went wrong. Please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        return view
    }
}
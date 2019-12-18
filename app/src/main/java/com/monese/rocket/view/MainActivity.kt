package com.monese.rocket.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

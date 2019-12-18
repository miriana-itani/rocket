package com.monese.rocket.di

import com.monese.rocket.view.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragmentModule(): MainFragment

}
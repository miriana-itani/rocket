package com.monese.rocket.di

import android.app.Application
import com.monese.rocket.RocketApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidInjectionModule::class,AndroidSupportInjectionModule::class,
    FragmentModule::class,ActivityModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }

    fun inject(application: RocketApp)
}
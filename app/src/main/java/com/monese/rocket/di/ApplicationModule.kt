package com.monese.rocket.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.monese.rocket.model.local.DaoAccess
import com.monese.rocket.model.local.RocketDatabase
import com.monese.rocket.model.networking.apis.GetLaunches
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'hh:mm.ss.SSSZ").create()
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .baseUrl("https://api.spacexdata.com/v3/")
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitGetLaunchesData(retrofit: Retrofit): GetLaunches {
        return retrofit.create(GetLaunches::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): RocketDatabase {
        return Room
            .databaseBuilder(context, RocketDatabase::class.java, "RocketDatabase.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: RocketDatabase): DaoAccess {
        return db.daoAccess()
    }


}
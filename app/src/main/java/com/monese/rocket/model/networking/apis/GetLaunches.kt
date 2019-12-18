package com.monese.rocket.model.networking.apis

import com.monese.rocket.model.models.Launch
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface GetLaunches {

    @GET("launches")
    fun fetchLaunches():Observable<MutableList<Launch>>
}
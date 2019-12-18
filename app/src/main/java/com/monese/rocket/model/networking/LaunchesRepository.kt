package com.monese.rocket.model.networking

import androidx.lifecycle.LiveData
import com.monese.rocket.model.local.DaoAccess
import com.monese.rocket.model.models.Launch
import com.monese.rocket.model.networking.apis.GetLaunches
import com.monese.rocket.vo.NetworkBoundResource
import com.monese.rocket.vo.Resource
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LaunchesRepository @Inject constructor(
    private val getLaunches: GetLaunches,
    private val rocketDao: DaoAccess
) {

    fun loadContents(refresh:Boolean?): LiveData<Resource<MutableList<Launch>>> {
        return object : NetworkBoundResource<MutableList<Launch>, MutableList<Launch>>(refresh) {
            override fun saveCallResult(request: MutableList<Launch>) {
                rocketDao.insertLaunches(request)
            }

            override fun loadFromDb(): Flowable<MutableList<Launch>> {
                return rocketDao.allLaunches
            }

            override fun createCall(): Observable<MutableList<Launch>> {
                return getLaunches.fetchLaunches()
            }



        }.asLiveData()
    }

}
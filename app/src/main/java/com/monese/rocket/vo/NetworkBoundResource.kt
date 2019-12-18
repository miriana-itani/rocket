package com.monese.rocket.vo

import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception


abstract class NetworkBoundResource<ResultType, RequestType>(private val shouldRefresh: Boolean?) {
    private lateinit var result: Flowable<Resource<ResultType>>

    init {
        build()
    }

    private fun build() {
        val source: Flowable<Resource<ResultType>> = createCall()
            // Request API on IO Scheduler
            .subscribeOn(Schedulers.io())
            // Read/Write to disk on Computation Scheduler
            .observeOn(Schedulers.computation())
            .doOnNext {
                saveCallResult(it)
            }
            .flatMap {
                loadFromDb().toObservable().map {
                    Resource.success(it)
                }
            }
            .toFlowable(BackpressureStrategy.LATEST)

        result = if(shouldRefresh==null||!shouldRefresh) {
            Flowable.concat(
                initLoadDb()!!
                    .map {
                        Resource.success(it)
                    }.take(1),
                source.onErrorReturn {
                    Resource.error(null, null)
                })
                .subscribeOn(Schedulers.io())
        }else{
            source.startWith(Resource.loading(null))
                .onErrorReturn {
                Resource.error(null,null)
            }
        }
    }


    open fun asLiveData(): LiveData<Resource<ResultType>> {
        return LiveDataReactiveStreams.fromPublisher(result)
    }

    open fun asObservable(): Observable<Resource<ResultType>> {
        return result.toObservable()
    }

    /*private fun processResponse(response: Response<RequestType>): RequestType {
        return response.body()!!
    }*/

    protected abstract fun saveCallResult(request: RequestType)

    protected abstract fun loadFromDb(): Flowable<ResultType>

    protected abstract fun createCall(): Observable<RequestType>

    @NonNull
    @MainThread
    protected open fun initLoadDb(): Flowable<ResultType>? {
        return loadFromDb()
    }

}

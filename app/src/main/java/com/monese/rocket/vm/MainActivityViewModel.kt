package com.monese.rocket.vm

import androidx.lifecycle.*
import com.monese.rocket.model.models.Launch
import com.monese.rocket.model.networking.LaunchesRepository
import com.monese.rocket.vo.Resource
import javax.inject.Inject


class MainActivityViewModel
@Inject constructor(
    private val rateListingRepository: LaunchesRepository
) :
    ViewModel() {

    private val refresh = MutableLiveData<Boolean>()

    val results: LiveData<Resource<MutableList<Launch>>> = Transformations
        .switchMap(refresh) { _ ->
            rateListingRepository.loadContents(refresh.value)
        }


    fun setRefresh(value: Boolean) {
        refresh.value = value
    }

}
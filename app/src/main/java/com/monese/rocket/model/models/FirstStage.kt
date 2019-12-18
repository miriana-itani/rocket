package com.monese.rocket.model.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FirstStage(
    val cores: List<Core>?
):Parcelable
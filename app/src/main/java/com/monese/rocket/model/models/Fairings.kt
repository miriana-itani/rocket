package com.monese.rocket.model.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fairings(
    val recovered: Boolean?,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean?,
    val reused: Boolean?,
    val ship: String?
):Parcelable
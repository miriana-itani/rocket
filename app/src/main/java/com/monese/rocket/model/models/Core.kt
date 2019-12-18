package com.monese.rocket.model.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Core(
    val block: Int?,
    @SerializedName("core_serial")
    val coreSerial: String?,
    val flight: Int?,
    val gridfins: Boolean?,
    @SerializedName("land_success")
    val landSuccess: Boolean?,
    @SerializedName("landing_intent")
    val landingIntent: Boolean?,
    @SerializedName("landing_type")
    val landingType: String?,
    @SerializedName("landing_vehicle")
    val landingVehicle: String?,
    val legs: Boolean?,
    val reused: Boolean?
):Parcelable
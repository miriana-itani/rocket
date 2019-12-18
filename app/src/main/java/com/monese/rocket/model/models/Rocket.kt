package com.monese.rocket.model.models


import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rocket(
    @SerializedName("fairings")
    @Embedded
    val fairings: Fairings?,
    @SerializedName("first_stage")
    @Embedded
    val firstStage: FirstStage?,
    @SerializedName("rocket_id")
    val rocketId: String?,
    @SerializedName("rocket_name")
    val rocketName: String?,
    @SerializedName("rocket_type")
    val rocketType: String?,
    @SerializedName("second_stage")
    @Embedded
    val secondStage: SecondStage?
):Parcelable
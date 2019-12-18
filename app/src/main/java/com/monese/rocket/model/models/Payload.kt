package com.monese.rocket.model.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Payload(
    val manufacturer: String?,
    val nationality: String?,
    val orbit: String?,
    @SerializedName("payload_id")
    val payloadId: String?,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Double?,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Double?,
    @SerializedName("payload_type")
    val payloadType: String?,
    val reused: Boolean?,
    val uid: String?
):Parcelable
package com.monese.rocket.model.models


import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


@Entity(indices = [Index(value = ["flightNumber"], unique = true)])
@Parcelize
class Launch(
    @PrimaryKey(autoGenerate = true)
    var launchGeneratedId: Int = 0,
    var details: String? = null,
    @SerializedName("flight_number")
    var flightNumber: Int? = null,
    @SerializedName("launch_date_utc")
    var lastDateUpdate: Date? = null,
    @SerializedName("links")
    @Embedded
    var links: Links? = null,
    @SerializedName("mission_id")
    var missionId: List<String>? = null,
    @SerializedName("mission_name")
    var missionName: String? = null,
    @Embedded
    var rocket: Rocket? = null,
    var tbd: Boolean? = null
) : Parcelable
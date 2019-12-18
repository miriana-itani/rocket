package com.monese.rocket.model.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String?,
    @SerializedName("youtube_id")
    val youtubeId: String? = null
) : Parcelable
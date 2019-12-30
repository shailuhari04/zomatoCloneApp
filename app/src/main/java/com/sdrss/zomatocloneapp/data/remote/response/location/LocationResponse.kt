package com.sdrss.zomatocloneapp.data.remote.response.location

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class LocationResponse(
    val has_more: Int = 0, // 0
    val has_total: Int = 0, // 0
    val location_suggestions: List<LocationSuggestion> = listOf(),
    val status: String = "", // success
    val user_has_addresses: Boolean = false // true
) : Parcelable
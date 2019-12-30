package com.sdrss.zomatocloneapp.data.remote.response.location


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class LocationSuggestion(
    val city_id: Int = 0, // 11633
    val city_name: String = "", // Seoni
    val country_id: Int = 0, // 1
    val country_name: String = "", // India
    val entity_id: Int = 0, // 11633
    val entity_type: String = "", // city
    var latitude: Double = 0.0, // 22.1073905
    var longitude: Double = 0.0, // 79.5685672
    var title: String = "" // Seoni
) : Parcelable
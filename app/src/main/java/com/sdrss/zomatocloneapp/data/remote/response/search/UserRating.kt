package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep
import com.sdrss.zomatocloneapp.data.remote.response.search.RatingObj

@Keep
data class UserRating(
    val aggregate_rating: String = "", // 3.4
    val rating_color: String = "", // CDD614
    val rating_obj: RatingObj = RatingObj(),
    val rating_text: String = "", // Average
    val votes: String = "" // 7
)
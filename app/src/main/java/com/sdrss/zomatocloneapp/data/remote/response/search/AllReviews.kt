package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class AllReviews(
    val reviews: List<Review> = listOf()
)
package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep
import com.sdrss.zomatocloneapp.data.remote.response.search.Restaurant

@Keep
data class SearchResultResponse(
    val restaurants: List<Restaurant> = listOf(),
    val results_found: Int = 0, // 1534899
    val results_shown: Int = 0, // 2
    val results_start: Int = 0 // 0
)
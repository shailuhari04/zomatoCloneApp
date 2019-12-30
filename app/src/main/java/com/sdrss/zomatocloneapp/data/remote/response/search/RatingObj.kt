package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class RatingObj(
    val bg_color: BgColor = BgColor(),
    val title: Title = Title()
)
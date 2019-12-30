package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class HasMenuStatus(
    val delivery: Int = 0, // -1
    val takeaway: Int = 0 // -1
)
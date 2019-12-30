package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class R(
    val has_menu_status: HasMenuStatus = HasMenuStatus(),
    val res_id: Int = 0 // 17839529
)
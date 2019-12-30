package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class Location(
    val address: String = "", // Ružinovská (zastávka Herlianska), Ružinov, Bratislava II
    val city: String = "", // Bratislava II
    val city_id: Int = 0, // 111
    val country_id: Int = 0, // 185
    val latitude: String = "", // 48.1567610000
    val locality: String = "", // Ružinov
    val locality_verbose: String = "", // Ružinov, Bratislava II
    val longitude: String = "", // 17.1558180000
    val zipcode: String = ""
)
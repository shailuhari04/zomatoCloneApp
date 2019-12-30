package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class PhotoX(
    val caption: String = "",
    val friendly_time: String = "", // Aug 10, 2015
    val height: Int = 0, // 640
    val id: String = "", // u_TkxNjMNDM0MTg5
    val res_id: Int = 0, // 17839529
    val thumb_url: String = "", // https://b.zmtcdn.com/data/reviews_photos/102/ab24a75439bc3cbc94963faa51769102_1439213730.jpg?impolicy=newcropandfit&cropw=1200&croph=1200&cropoffsetx=0&cropoffsety=0&cropgravity=NorthWest&fitw=200&fith=200&fittype=ignore
    val timestamp: Int = 0, // 1439213731
    val url: String = "", // https://b.zmtcdn.com/data/reviews_photos/102/ab24a75439bc3cbc94963faa51769102_1439213730.jpg?fit=around%7C640%3A640&crop=640%3A640%3B%2A%2C%2A
    val user: User = User(),
    val width: Int = 0 // 640
)
package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class User(
    val foodie_color: String = "", // f58552
    val foodie_level: String = "", // Super Foodie
    val foodie_level_num: Int = 0, // 10
    val name: String = "", // Eva Vargová
    val profile_deeplink: String = "", // zomato://u/31213998
    val profile_image: String = "", // https://b.zmtcdn.com/data/user_profile_pictures/4b3/7551cff30aefcc315f9591b13666c4b3.jpg?fit=around%7C100%3A100&crop=100%3A100%3B%2A%2C%2A
    val profile_url: String = "" // https://www.zomato.com/users/eva-vargov-31213998?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
)
package com.sdrss.zomatocloneapp.data.remote.response.search


import androidx.annotation.Keep

@Keep
data class RestaurantX(
    val R: R = R(),
    val all_reviews: AllReviews = AllReviews(),
    val all_reviews_count: Int = 0, // 5
    val apikey: String = "", // 4feaa2167c4dc6beadf629319423bd4b
    val average_cost_for_two: Int = 0, // 2
    val book_again_url: String = "",
    val book_form_web_view_url: String = "",
    val cuisines: String = "", // Ice Cream
    val currency: String = "", // €
    val deeplink: String = "", // zomato://restaurant/17839529
    val establishment: List<String> = listOf(),
    val establishment_types: List<Any> = listOf(),
    val events_url: String = "", // https://www.zomato.com/bratislava/zmrzlina-u-aziriho-ružinov-bratislava-ii/events#tabtop?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
    val featured_image: String = "", // https://b.zmtcdn.com/data/reviews_photos/698/6710244587e66416039e21da9a99e698_1494272694.jpg?fit=around%7C640%3A640&crop=640%3A640%3B%2A%2C%2A
    val has_online_delivery: Int = 0, // 0
    val has_table_booking: Int = 0, // 0
    val highlights: List<String> = listOf(),
    val id: String = "", // 17839529
    val include_bogo_offers: Boolean = false, // true
    val is_book_form_web_view: Int = 0, // 0
    val is_delivering_now: Int = 0, // 0
    val is_table_reservation_supported: Int = 0, // 0
    val is_zomato_book_res: Int = 0, // 0
    val location: Location = Location(),
    val menu_url: String = "", // https://www.zomato.com/bratislava/zmrzlina-u-aziriho-ružinov-bratislava-ii/menu?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1&openSwipeBox=menu&showMinimal=1#tabtop
    val mezzo_provider: String = "", // OTHER
    val name: String = "", // Zmrzlina u Aziriho
    val offers: List<Any> = listOf(),
    val opentable_support: Int = 0, // 0
    val phone_numbers: String = "", // Not available for this place
    val photo_count: Int = 0, // 4
    val photos: List<Photo> = listOf(),
    val photos_url: String = "", // https://www.zomato.com/bratislava/zmrzlina-u-aziriho-ružinov-bratislava-ii/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop
    val price_range: Int = 0, // 1
    val switch_to_order_menu: Int = 0, // 0
    val thumb: String = "", // https://b.zmtcdn.com/data/reviews_photos/698/6710244587e66416039e21da9a99e698_1494272694.jpg?impolicy=newcropandfit&cropw=1200&croph=1200&cropoffsetx=0&cropoffsety=378&cropgravity=NorthWest&fitw=200&fith=200&fittype=ignore
    val timings: String = "", // 10:00 to 20:00 (Mon-Sun)
    val url: String = "", // https://www.zomato.com/bratislava/zmrzlina-u-aziriho-ružinov-bratislava-ii?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
    val user_rating: UserRating = UserRating()
)
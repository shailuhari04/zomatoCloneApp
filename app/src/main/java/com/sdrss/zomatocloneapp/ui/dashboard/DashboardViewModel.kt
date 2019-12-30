package com.sdrss.zomatocloneapp.ui.dashboard

import androidx.databinding.ObservableBoolean
import com.sdrss.zomatocloneapp.base.BaseViewModel
import com.sdrss.zomatocloneapp.data.remote.api.SearchAPI
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion
import com.sdrss.zomatocloneapp.data.remote.response.search.Restaurant
import com.sdrss.zomatocloneapp.extension.with
import com.sdrss.zomatocloneapp.utils.NotNullMutableLiveData

class DashboardViewModel(private val api: SearchAPI) : BaseViewModel() {

    private var query: String = ""
        get() = if (field.isEmpty()) "Mumbai" else field

    private val _resultLiveData: NotNullMutableLiveData<List<Restaurant>> = NotNullMutableLiveData(
        mutableListOf()
    )
    val resultLiveData: NotNullMutableLiveData<List<Restaurant>>
        get() = _resultLiveData

    lateinit var locationSuggestion: LocationSuggestion

    var latitude = 0.0

    var longitude = 0.0

    var isInternetAvailable = ObservableBoolean(false)

    private val _clearAdapterData: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val clearAdapterData: NotNullMutableLiveData<Boolean>
        get() = _clearAdapterData


    var offsetValue = 0

    fun loadNextOffsetVale() = run {
        offsetValue++
        doSearch()
    }

    fun doSearch() {
        val params = mutableMapOf<String, Any>().apply {
            this["entity_type"] = "city"
            this["sort"] = "cost"
            this["order"] = "desc"
            this["start"] = offsetValue
        }

        if (latitude != 0.0 && longitude != 0.0) {
            params["lat"] = latitude
            params["lon"] = longitude
        } else {
            params.apply {
                this["q"] = query
            }
        }

        if (::locationSuggestion.isInitialized) {
            locationSuggestion.let {
                params["entity_id"] = it.entity_id
                params["entity_type"] = it.entity_type
                params["lat"] = it.latitude
                params["lon"] = it.longitude
            }
        }

        addToDisposable(api.searchData(params).with()
            .doOnNext {
                _resultLiveData.value = it.restaurants
            }
            .subscribe({
                _resultLiveData.value = it.restaurants
            }, {
                // handle errors
            })
        )
    }
}

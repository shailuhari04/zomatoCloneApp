package com.sdrss.zomatocloneapp.ui.search

import androidx.databinding.ObservableBoolean
import com.sdrss.zomatocloneapp.base.BaseViewModel
import com.sdrss.zomatocloneapp.data.remote.api.SearchAPI
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion
import com.sdrss.zomatocloneapp.extension.with
import com.sdrss.zomatocloneapp.utils.NotNullMutableLiveData

class LocationSearchViewModel(private val api: SearchAPI) : BaseViewModel() {

    private var query: String = ""
        get() = if (field.isEmpty()) "Mumbai" else field

    private val _resultLiveData: NotNullMutableLiveData<List<LocationSuggestion>> =
        NotNullMutableLiveData(
            mutableListOf()
        )
    val resultLiveData: NotNullMutableLiveData<List<LocationSuggestion>>
        get() = _resultLiveData

    private val _requestForLocationLiveData: NotNullMutableLiveData<Boolean> =
        NotNullMutableLiveData(
            false
        )
    val requestForLocationLiveData: NotNullMutableLiveData<Boolean>
        get() = _requestForLocationLiveData

    var isClearSearchInput = ObservableBoolean(true)

    fun doSearch() {
        val params = mutableMapOf<String, Any>().apply {
            this["query"] = query
        }

        addToDisposable(api.locationData(params).with()
            .doOnNext {
                _resultLiveData.value = it.location_suggestions
            }
            .subscribe({
                _resultLiveData.value = it.location_suggestions
            }, {
                // handle errors
            })
        )
    }

    fun onQueryChange(query: CharSequence) {
        isClearSearchInput.set(false)
        this.query = query.toString()
        doSearch()
    }

    fun getCurrentLocation() {
        _requestForLocationLiveData.value = true
    }
}

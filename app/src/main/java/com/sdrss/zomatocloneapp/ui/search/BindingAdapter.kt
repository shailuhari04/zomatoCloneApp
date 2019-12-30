package com.sdrss.zomatocloneapp.ui.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion

@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(
    view: RecyclerView,
    items: MutableList<LocationSuggestion>,
    vm: LocationSearchViewModel
) {
    view.adapter?.run {
        if (this is LocationSuggestionListAdapter) {
            this.data = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        LocationSuggestionListAdapter(vm).apply {
            this.data = items
            view.adapter = this
        }
    }
}
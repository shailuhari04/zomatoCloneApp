package com.sdrss.zomatocloneapp.ui.dashboard

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sdrss.zomatocloneapp.data.remote.response.search.Restaurant
import com.sdrss.zomatocloneapp.ui.search.LocationSearchViewModel

@androidx.databinding.BindingAdapter("setBackgroundColor")
fun setBackgroundColor(view: View, hexColor: String?) {
    hexColor?.let {
        if (view is TextView) {
            if (it.contains("#")) {
                view.setBackgroundColor(Color.parseColor(it))
            } else {
                view.setBackgroundColor(Color.parseColor("#$it"))
            }
        }
    }
}


@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(view: RecyclerView, items: MutableList<Restaurant>, vm: DashboardViewModel) {
    view.adapter?.run {
        if (this is SearchResultListAdapter) {
            this.data = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        SearchResultListAdapter(vm).apply {
            this.data = items
            view.adapter = this
        }
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}
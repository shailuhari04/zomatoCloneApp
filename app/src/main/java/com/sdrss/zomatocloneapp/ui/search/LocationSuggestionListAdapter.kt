package com.sdrss.zomatocloneapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdrss.zomatocloneapp.R
import com.sdrss.zomatocloneapp.binding.BindingViewHolder
import com.sdrss.zomatocloneapp.data.remote.response.location.LocationSuggestion
import com.sdrss.zomatocloneapp.databinding.RowItemLocationSuggestionBinding

class LocationSuggestionListAdapter(val vm: LocationSearchViewModel) :
    RecyclerView.Adapter<LocationSuggestionListAdapter.ViewHolder>() {

    var data = mutableListOf<LocationSuggestion>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    var listener: (location: LocationSuggestion) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_location_suggestion, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.item = item
        holder.binding.vm = vm
        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }
    }

    inner class ViewHolder(itemView: View) :
        BindingViewHolder<RowItemLocationSuggestionBinding>(itemView)
}
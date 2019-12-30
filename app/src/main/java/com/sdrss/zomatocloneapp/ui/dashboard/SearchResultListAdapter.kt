package com.sdrss.zomatocloneapp.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdrss.zomatocloneapp.R
import com.sdrss.zomatocloneapp.binding.BindingViewHolder
import com.sdrss.zomatocloneapp.data.remote.response.search.Restaurant
import com.sdrss.zomatocloneapp.databinding.RowItemBinding

class SearchResultListAdapter(val vm: DashboardViewModel) :
    RecyclerView.Adapter<SearchResultListAdapter.ViewHolder>() {

    var clearData: Boolean = false

    var data = mutableListOf<Restaurant>()
        set(value) {
            if (clearData)
                field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.item = item
        holder.binding.vm = vm
        if (position == data.size - 5) {
            vm.loadNextOffsetVale()
        }
    }

    inner class ViewHolder(itemView: View) : BindingViewHolder<RowItemBinding>(itemView)
}
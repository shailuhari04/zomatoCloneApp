package com.sdrss.zomatocloneapp.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sdrss.zomatocloneapp.R
import com.sdrss.zomatocloneapp.binding.BindingFragment
import com.sdrss.zomatocloneapp.databinding.DashboardFragmentBinding
import com.sdrss.zomatocloneapp.utils.isOnline
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DashboardFragment : BindingFragment<DashboardFragmentBinding>() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var adapterSearchResult: SearchResultListAdapter

    override fun getLayoutResId(): Int = R.layout.dashboard_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //get sharedViewModel
        val vm = activity?.getViewModel<DashboardViewModel>()

        //setViewModel to Binding
        binding.vm = vm

        //loadData
        context?.let {
            if (it.isOnline()) {
                vm?.isInternetAvailable?.set(true)
                vm?.doSearch()
            } else {
                vm?.isInternetAvailable?.set(false)
            }
        }

        vm?.clearAdapterData?.observe(this, Observer {
            if (it) {
                if (::adapterSearchResult.isInitialized) {
                    adapterSearchResult.clearData = true
                    vm.offsetValue = 0
                }
            }
        })

        //Observe Data and setData
        vm?.resultLiveData?.observe(this, Observer { dataList ->
            adapterSearchResult.data = dataList.toMutableList()
        })
        adapterSearchResult = vm?.let { SearchResultListAdapter(it) }!!
        recyclerView.adapter = adapterSearchResult
    }
}

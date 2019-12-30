package com.sdrss.zomatocloneapp.di

import com.sdrss.zomatocloneapp.ui.dashboard.DashboardViewModel
import com.sdrss.zomatocloneapp.ui.search.LocationSearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author SDRSS
 */
val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { LocationSearchViewModel(get()) }
}
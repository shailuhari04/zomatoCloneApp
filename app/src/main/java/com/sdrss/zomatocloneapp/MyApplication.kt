package com.sdrss.zomatocloneapp

import android.app.Application
import com.sdrss.zomatocloneapp.di.apiModule
import com.sdrss.zomatocloneapp.di.networkModule
import com.sdrss.zomatocloneapp.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin(
            this, listOf(
                networkModule,
                apiModule,
                viewModelModule
            )
        )
    }
}
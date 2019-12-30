package com.sdrss.zomatocloneapp.di

import com.sdrss.zomatocloneapp.data.remote.api.SearchAPI
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 * @author SDRSS
 */
val apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(SearchAPI::class.java) }
}




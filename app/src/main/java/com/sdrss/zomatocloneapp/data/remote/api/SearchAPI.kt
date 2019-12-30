package com.sdrss.zomatocloneapp.data.remote.api

import com.sdrss.zomatocloneapp.data.remote.response.location.LocationResponse
import com.sdrss.zomatocloneapp.data.remote.response.search.SearchResultResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author SDRSS
 */
interface SearchAPI {

    @GET("api/v2.1/search")
    fun searchData(@QueryMap params: MutableMap<String, Any>): Observable<SearchResultResponse>

    @GET("api/v2.1/locations")
    fun locationData(@QueryMap params: MutableMap<String, Any>): Observable<LocationResponse>
}
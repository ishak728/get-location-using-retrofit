package com.ishak.locationproject.Service

import com.ishak.locationproject.Model.Location
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LocationApiService {

    private val BASE_URL="https://gist.githubusercontent.com/"

    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LocationApi::class.java)

    fun getData():Single<List<Location>>{
        return api.getLocation()
    }
}
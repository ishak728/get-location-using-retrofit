package com.ishak.locationproject.Service

import com.ishak.locationproject.Model.Location
import io.reactivex.Single
import retrofit2.http.GET

interface LocationApi {

    //https://gist.githubusercontent.com/tdreyno/4278655/raw/7b0762c09b519f40397e4c3e100b097d861f5588/airports.json

    @GET("tdreyno/4278655/raw/7b0762c09b519f40397e4c3e100b097d861f5588/airports.json")
    fun getLocation():Single<List<Location>>
}
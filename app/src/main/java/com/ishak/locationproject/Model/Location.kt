package com.ishak.locationproject.Model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val Country:String?,
    @SerializedName("lon")
val longitude:String?,
    @SerializedName("lat")
    val latitude:String?


)
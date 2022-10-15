package com.rogernkosi.absaassessment.data.remote.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("data")
    val `data`: Data
)
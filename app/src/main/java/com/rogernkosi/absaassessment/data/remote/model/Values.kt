package com.rogernkosi.absaassessment.data.remote.model


import com.google.gson.annotations.SerializedName

data class Values(
    @SerializedName("temperature")
    val temperature: Double
)
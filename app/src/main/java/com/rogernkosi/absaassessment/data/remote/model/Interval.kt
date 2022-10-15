package com.rogernkosi.absaassessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class Interval(
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("values")
    val values: Values
)
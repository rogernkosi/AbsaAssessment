package com.rogernkosi.absaassessment.data.remote.model


import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("intervals")
    val intervals: List<Interval>,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("timestep")
    val timestep: String
)
package com.rogernkosi.absaassessment.data.remote.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("timelines")
    val timelines: List<Timeline>
)
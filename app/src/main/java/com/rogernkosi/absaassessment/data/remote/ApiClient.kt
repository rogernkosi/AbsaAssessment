package com.rogernkosi.absaassessment.data.remote

import com.rogernkosi.absaassessment.data.remote.model.Data
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiClient {
    @Headers(
        "accept", "application/json",
        "Accept-Encoding", "gzip"
    )
    @GET("/timelines")
    suspend fun getWeatherTimelines(): Data
}
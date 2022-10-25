package com.rogernkosi.absaassessment.data.remote

import com.rogernkosi.absaassessment.data.remote.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("timelines")
    suspend fun getWeatherTimelines(
        @Query("location") location: String,
        @Query("fields") fields: List<String>,
        @Query("timesteps") timesteps: String,
        @Query("units") units: String,
        @Query("apikey") apikey: String
    ): Weather
}
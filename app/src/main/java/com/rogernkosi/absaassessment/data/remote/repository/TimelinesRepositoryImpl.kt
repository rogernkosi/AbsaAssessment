package com.rogernkosi.absaassessment.data.remote.repository

import com.rogernkosi.absaassessment.commons.Constants
import com.rogernkosi.absaassessment.data.remote.ApiClient
import com.rogernkosi.absaassessment.domain.model.Weather
import com.rogernkosi.absaassessment.domain.repository.TimelinesRepository

import javax.inject.Inject

class TimelinesRepositoryImpl @Inject constructor(private val api: ApiClient) :
    TimelinesRepository {
    override suspend fun getTimelines(): Map<String, List<Weather>> {

        val data = api.getWeatherTimelines(
            Constants.LOCATION,
            Constants.FIELDS,
            Constants.TIME_STEPS,
            Constants.UNITS,
            Constants.API_KEY
        ).data

        val transformedData = data.timelines.map { timeline ->
            timeline.intervals.map { intervals ->
                Weather(
                    timeline.endTime,
                    timeline.startTime,
                    timeline.timestep,
                    intervals.startTime,
                    intervals.values,
                )
            }
        }.flatten()

        val groupedData = transformedData.groupBy {
            it.intervalStartTime
        }
        return groupedData
    }
}
package com.rogernkosi.absaassessment.data.remote.repository

import com.rogernkosi.absaassessment.commons.Constants
import com.rogernkosi.absaassessment.data.remote.ApiClient
import com.rogernkosi.absaassessment.domain.model.TimeLine
import com.rogernkosi.absaassessment.domain.model.Weather
import com.rogernkosi.absaassessment.domain.repository.TimelinesRepository
import org.joda.time.DateTime
import org.joda.time.Days

import javax.inject.Inject

class TimelinesRepositoryImpl @Inject constructor(private val api: ApiClient) :
    TimelinesRepository {

    private var weather = mutableListOf<Weather>()
    private val finalWeather = mutableListOf<Weather>()

    override suspend fun getTimelines(): List<Weather> {
        val data = api.getWeatherTimelines(
            Constants.LOCATION,
            Constants.FIELDS,
            Constants.TIME_STEPS,
            Constants.UNITS,
            Constants.API_KEY
        ).data

        data.timelines.forEach { timeline ->
            val start = DateTime.parse(timeline.startTime)
            val end = DateTime.parse(timeline.endTime)
            val numDays = Days.daysBetween(start, end).days
            for (i in 0..numDays) {
                weather.add(Weather(start.plusDays(i).toString(), null))
            }

            weather.forEachIndexed { i, _weather ->
                val tmp = mutableListOf<TimeLine>()

                for (interval in timeline.intervals) {
                    if (_weather.startTime.regionMatches(0, interval.startTime, 0, 10)) {
                        tmp.add(
                            TimeLine(
                                interval = interval.startTime,
                                temp = interval.values.temperature
                            )
                        )
                    }
                }
                _weather.timelines = tmp
                finalWeather.add(i, _weather)
            }
        }

        return finalWeather
    }
}
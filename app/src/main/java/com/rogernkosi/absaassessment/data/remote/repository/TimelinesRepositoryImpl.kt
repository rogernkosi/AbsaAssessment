package com.rogernkosi.absaassessment.data.remote.repository

import com.rogernkosi.absaassessment.data.remote.ApiClient
import com.rogernkosi.absaassessment.data.remote.model.Data
import com.rogernkosi.absaassessment.domain.repository.TimelinesRepository
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.collections.HashMap

class TimelinesRepositoryImpl @Inject constructor(private val api: ApiClient) :
    TimelinesRepository {
    override suspend fun getTimelines(): Data? {

        val sdf = SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ")

        val finalData :MutableMap<String, MutableList<String>?> = HashMap()

        val data = api.getWeatherTimelines()
//        data.timelines.map { it.intervals.binarySearch {  } }
        val end = data.timelines[data.timelines.size - 1].endTime
        val start = data.timelines[data.timelines.size - 1].startTime
        val intervals = data.timelines[data.timelines.size - 1].intervals

        val parsedEndDate = sdf.parse(end)
        val parsedStartDate = sdf.parse(start)

        while (parsedStartDate.before(parsedEndDate)){
            finalData[parsedStartDate.time.toString()] = null
        }

        intervals.forEach { intervals ->
            run {
                finalData.forEach {
                    if (it.key.contains(intervals.startTime)){
                        it.value?.add(intervals.startTime)
                    }
                }
            }
        }

        return null
    }
}
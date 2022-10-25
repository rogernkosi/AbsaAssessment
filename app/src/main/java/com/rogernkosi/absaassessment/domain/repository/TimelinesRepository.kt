package com.rogernkosi.absaassessment.domain.repository

import com.rogernkosi.absaassessment.domain.model.Weather

interface TimelinesRepository {
    suspend fun getTimelines(): List<Weather>
}
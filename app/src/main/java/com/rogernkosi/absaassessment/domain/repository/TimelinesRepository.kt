package com.rogernkosi.absaassessment.domain.repository

import com.rogernkosi.absaassessment.data.remote.model.Data

interface TimelinesRepository {
    suspend fun getTimelines(): Data
}
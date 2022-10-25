package com.rogernkosi.absaassessment.domain.use_cases

import com.rogernkosi.absaassessment.R
import com.rogernkosi.absaassessment.commons.Resource
import com.rogernkosi.absaassessment.domain.model.Weather
import com.rogernkosi.absaassessment.domain.repository.TimelinesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTimelinesUseCase @Inject constructor(private val repository: TimelinesRepository) {
    operator fun invoke(): Flow<Resource<List<Weather>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getTimelines()))
        } catch (e: HttpException) {
            emit(Resource.Error(R.string.network_failure))
        } catch (e: IOException) {
            emit(Resource.Error(R.string.connection_failure))
        }
    }
}
package com.rogernkosi.absaassessment.ui.timelines

import androidx.annotation.StringRes
import com.rogernkosi.absaassessment.domain.model.Weather

sealed interface WeatherState{
    data class Success(val weather:  List<Weather> = emptyList()) : WeatherState
    data class Error(@StringRes val error: Int? = 0) : WeatherState
    object Loading : WeatherState
}
package com.rogernkosi.absaassessment.ui.timelines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogernkosi.absaassessment.commons.Resource
import com.rogernkosi.absaassessment.domain.use_cases.GetTimelinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherTimelinesViewModel @Inject constructor(private val getTimelinesUseCase: GetTimelinesUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<WeatherState>()
    val state: LiveData<WeatherState> = _state

    init {
        getWeather()
    }

    private fun getWeather() {
        getTimelinesUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = WeatherState.Loading
                }
                is Resource.Success -> {
                    _state.value = WeatherState.Success(it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = WeatherState.Error(it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
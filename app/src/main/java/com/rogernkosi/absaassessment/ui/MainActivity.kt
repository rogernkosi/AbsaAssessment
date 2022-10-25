package com.rogernkosi.absaassessment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogernkosi.absaassessment.databinding.ActivityMainBinding
import com.rogernkosi.absaassessment.domain.model.Weather
import com.rogernkosi.absaassessment.domain.use_cases.GetTimelinesUseCase
import com.rogernkosi.absaassessment.ui.timelines.WeatherAdapter
import com.rogernkosi.absaassessment.ui.timelines.WeatherState
import com.rogernkosi.absaassessment.ui.timelines.WeatherTimelinesViewModel
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var timelines: GetTimelinesUseCase

    private val weatherViewModel: WeatherTimelinesViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getState()
    }

    private fun getState() {
        weatherViewModel.state.observe(this) { state ->
            when (state) {
                is WeatherState.Error -> {
                   //show error snack bar with erorr message
                }
                WeatherState.Loading -> {
                    // show loading bar
                }
                is WeatherState.Success -> {
                    setUpWeatherList(state.weather)
                }
            }
        }
    }

    private fun setUpWeatherList(weather: List<Weather>) {
        binding.list.adapter = WeatherAdapter(weather)
        binding.list.layoutManager = LinearLayoutManager(baseContext)
        val dividerItemDecoration =
            DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }
}
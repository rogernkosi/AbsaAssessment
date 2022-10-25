package com.rogernkosi.absaassessment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rogernkosi.absaassessment.databinding.ActivityMainBinding
import com.rogernkosi.absaassessment.domain.model.RecyclerViewItem
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
                    showErrorMessage(state.error!!)
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
        val dataSet = arrayListOf<RecyclerViewItem>()

        val weatherAdapter = WeatherAdapter()

        weather.forEach {
            dataSet.add(RecyclerViewItem.SectionItem(it.startTime))
            it.timelines?.forEach { timeLine ->
                dataSet.add(RecyclerViewItem.ContentItem(timeLine.interval, timeLine.temp))
            }
        }
        dataSet.also { weatherAdapter.data = it }

        binding.list.adapter = weatherAdapter
        binding.list.layoutManager = LinearLayoutManager(baseContext)
        val dividerItemDecoration =
            DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun showErrorMessage(@StringRes error: Int = 0) {
        Snackbar.make(binding.coordinator, getString(error), Snackbar.LENGTH_SHORT).show()
    }
}
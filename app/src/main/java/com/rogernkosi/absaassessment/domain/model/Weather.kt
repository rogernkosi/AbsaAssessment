package com.rogernkosi.absaassessment.domain.model

data class Weather(
    var startTime: String,
    var timelines: List<TimeLine>?
)
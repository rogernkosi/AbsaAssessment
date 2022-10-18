package com.rogernkosi.absaassessment.commons

import java.text.SimpleDateFormat

object Constants {
    const val BASE_URL = "https://api.tomorrow.io/v4/"
    const val LOCATION = "26,27.8540"
    const val TIME_STEPS = "1h"
    val FIELDS = listOf("temperature")
    const val UNITS = "metric";
    const val API_KEY = "RlXXRh53t3SC2TmdIuiYaFcdzKVNs55W"
    val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
}
package com.rogernkosi.absaassessment.ui.utils

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class DateUtils {
    companion object{
        fun getDateFormat(format: String) : DateTimeFormatter = DateTimeFormat.forPattern(format)
        fun getDateTimeFormat(format: String) : DateTimeFormatter = DateTimeFormat.forPattern(format)
    }
}
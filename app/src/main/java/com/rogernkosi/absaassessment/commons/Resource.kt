package com.rogernkosi.absaassessment.commons

import androidx.annotation.StringRes

sealed class Resource<T>(val data: T? = null, @StringRes val message: Int? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(@StringRes message: Int, data: T? = null) :
        Resource<T>(message = message, data = data)

    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}

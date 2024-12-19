package com.mzs.core.domain.bo

sealed class Result<out T> {

    object Loading : Result<Nothing>()

    sealed class Response<out T> : Result<T>() {
        data class Success<out T>(val data: T) : Response<T>()
        data class Error<out E>(val code: E) : Response<Nothing>()
    }

}
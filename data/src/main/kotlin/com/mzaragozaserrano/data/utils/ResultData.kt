package com.mzaragozaserrano.data.utils

sealed class ResultData<out R> {

    data class Response<out R>(val data: R) : ResultData<R>()

    data class Error<out E>(val code: E) : ResultData<Nothing>()

}
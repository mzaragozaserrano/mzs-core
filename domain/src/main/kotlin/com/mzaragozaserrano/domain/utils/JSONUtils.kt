package com.mzaragozaserrano.domain.utils

import com.mzaragozaserrano.domain.bo.CoreErrorBO
import com.mzaragozaserrano.domain.bo.Result
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

inline fun <reified T : Any> convertJSONToBO(json: String): Result<T> {
    val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    return try {
        val dto: T? = moshiBuilder.adapter(T::class.java).fromJson(json)
        if (dto != null) {
            Result.Response.Success(dto)
        } else {
            Result.Response.Error(CoreErrorBO.DataNotFound)
        }
    } catch (ex: JsonDataException) {
        ex.printStackTrace()
        Result.Response.Error(CoreErrorBO.DeserializingJSON)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.Response.Error(CoreErrorBO.LoadingData)
    }
}
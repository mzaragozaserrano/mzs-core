package com.mzaragozaserrano.domain.bo

sealed class CoreErrorBO: ErrorBO {
    object Connectivity : CoreErrorBO()
    object DataNotFound : CoreErrorBO()
    object DeserializingJSON : CoreErrorBO()
    object LoadingData : CoreErrorBO()

}

sealed interface ErrorBO
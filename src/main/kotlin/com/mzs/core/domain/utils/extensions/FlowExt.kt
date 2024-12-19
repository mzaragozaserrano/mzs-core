package com.mzs.core.domain.utils.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> T.toFlowResult(): Flow<T> {
    val value = this
    return flow { emit(value) }
}
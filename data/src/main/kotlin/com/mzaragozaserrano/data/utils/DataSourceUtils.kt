package com.mzaragozaserrano.data.utils

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
fun <D, E> onError(
    continuation: CancellableContinuation<ResultData<D>>,
    error: E,
) {
    continuation.resume(ResultData.Error(error), null)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <D> onSuccess(
    continuation: CancellableContinuation<ResultData<D>>,
    data: D,
) {
    continuation.resume(ResultData.Response(data), null)
}
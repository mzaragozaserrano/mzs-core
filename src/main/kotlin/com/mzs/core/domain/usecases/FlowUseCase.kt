package com.mzs.core.domain.usecases

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.utils.extensions.toFlowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCase<in Params, out Response, out Error>(
    private val networkError: Error,
    private val networkRepository: NetworkRepository,
) where Response : Any {

    abstract suspend fun run(params: Params): Flow<Response>

    suspend operator fun invoke(params: Params): Flow<Response> =
        if (networkRepository.isConnected()) {
            run(params).flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(networkError) as Response).toFlowResult()
        }

}

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCaseNoParams<out Response, out Error>(
    private val networkError: Error,
    private val networkRepository: NetworkRepository,
) where Response : Any {

    abstract suspend fun run(): Flow<Response>

    suspend operator fun invoke(): Flow<Response> =
        if (networkRepository.isConnected()) {
            run().flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(networkError) as Response).toFlowResult()
        }

}
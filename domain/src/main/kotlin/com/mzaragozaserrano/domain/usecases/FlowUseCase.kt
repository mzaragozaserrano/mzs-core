package com.mzaragozaserrano.domain.usecases

import com.mzaragozaserrano.domain.repositories.NetworkRepository
import com.mzaragozaserrano.domain.utils.Result
import com.mzaragozaserrano.domain.utils.extension.toFlowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCase<in P, out R, out E>(
    private val networkError: E,
    private val networkRepository: NetworkRepository,
) where R : Any {

    abstract suspend fun run(params: P): Flow<R>

    suspend operator fun invoke(params: P): Flow<R> =
        if (networkRepository.isConnected()) {
            run(params).flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(networkError) as R).toFlowResult()
        }

}

@Suppress("UNCHECKED_CAST")
abstract class FlowUseCaseNoParams<out R, out E>(
    private val networkError: E,
    private val networkRepository: NetworkRepository,
) where R : Any {

    abstract suspend fun run(): Flow<R>

    suspend operator fun invoke(): Flow<R> =
        if (networkRepository.isConnected()) {
            run().flowOn(Dispatchers.IO)
        } else {
            (Result.Response.Error(networkError) as R).toFlowResult()
        }

}
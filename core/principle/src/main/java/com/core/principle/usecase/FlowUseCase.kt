package com.core.principle.usecase

import com.core.principle.result.AsyncTaskResult
import com.core.principle.result.loading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Part of MVVM Clean Arch.
 *
 * UseCase contains business Logic that cast the raw data into what can be consumed by UI by applying
 * business logic. This UseCase brings clear separation of business and View Data.
 *
 * The Raw data can be from DB, input from one or multiple server.
 */
abstract class FlowUseCase<T>(private val ioDispatcher: CoroutineDispatcher) {

    /**
     * Method to execute the usecase to perform some Task and get outcome from that task as [AsyncTaskResult]
     *
     * @return [Flow] of [AsyncTaskResult]
     */
    fun execute(): Flow<AsyncTaskResult<T>> = flow {
        emit(loading())
        emit(withContext(ioDispatcher) { executeInternal() })
    }

    /**
     * Method need to be implemented where UseCase perform the business logic
     */
    protected abstract fun executeInternal(): AsyncTaskResult<T>
}

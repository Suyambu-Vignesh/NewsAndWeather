package com.core.principle.result

/**
 * Base Interface Of Async Task Result which can be either Success/Error.
 */
sealed interface AsyncTaskResult<out T> {
    /**
     * Will return true, if the Task is a Loading Task
     */
    val isLoading: Boolean get() = this is Loading

    /**
     * Will return true, if the Task is a Succeeded Task
     */
    val isSuccess: Boolean get() = this is CompletedTask && this.result.isSuccess

    /**
     * Will return true, if the Task is a Failed Task
     */
    val isFailure: Boolean get() = this is CompletedTask && this.result.isFailure

    /**
     * Helper method which help to execute some action if the Task is failed.
     *
     * @param action - Action/callback that will happen if the task failed
     */
    fun onFailure(action: (error: Error) -> Unit): AsyncTaskResult<T> = apply {
        if (this is CompletedTask) {
            this.result.onFailure(action)
        }
    }

    /**
     * Helper method which help to execute some action if the Task is succeeded.
     *
     * @param action - Action/callback that will happen if the task succeeded
     */
    fun onSuccess(action: (data: T) -> Unit): AsyncTaskResult<T> = apply {
        if (this is CompletedTask) {
            this.result.onSuccess(action)
        }
    }
}

/**
 * Helper function to return the Loading Async Task
 */
fun <T> loading(): AsyncTaskResult<T> = LoadingImpl

/**
 * Helper function to return the Loading Completed Task
 */
fun <T> done(result: TaskResult<T, Error>): AsyncTaskResult<T> = CompletedTaskImpl(result)

/**
 * Base interface for Loading Task
 */
sealed interface Loading : AsyncTaskResult<Nothing>

/**
 * Base interface for Completed Task
 */
sealed interface CompletedTask<T> : AsyncTaskResult<T> {
    val result: TaskResult<T, Error>
}

/**
 * Imp of [Loading]
 */
internal object LoadingImpl : Loading

/**
 * Imp of [CompletedTask]
 */
internal class CompletedTaskImpl<T>(override val result: TaskResult<T, Error>) : CompletedTask<T>

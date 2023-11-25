package com.core.principle.result

/**
 * Base Interface Of Task Result which can be either Success/Error
 */
sealed interface TaskResult<out T, out E> {

    /**
     * Return's `true` if the Task is SuccessFull. The instance will be instance of [Success]
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Return's `true` if the Task is Failed. The instance will be instance of [Failure]
     */
    val isFailure: Boolean get() = this is Failure

    /**
     * Method return the TaskOutput if successFul or Null
     *
     * @return TaskOutput, instance of [T] or Null
     */
    fun getTaskData(): T?

    /**
     * Method return the TaskError if error or Null
     *
     * @return TaskError, instance of [E] or Null
     */
    fun getTaskError(): E?

    /**
     * Method that execute the action with Failed TaskOutput
     *
     * @param action - fun as parameter which accept the Task Failed Output
     */
    fun onFailure(action: (error: E) -> Unit) {
        this.getTaskError()?.let {
            action.invoke(it)
        }
    }

    /**
     * Method that execute the action with Succeeded TaskOutput
     *
     * @param action - fun as parameter which accept the Task Succeeded Output
     */
    fun onSuccess(action: (data: T) -> Unit) {
        this.getTaskData()?.let {
            action.invoke(it)
        }
    }
}

/**
 * Base Interface of SuccessFull [TaskResult]
 */
sealed interface Success<T> : TaskResult<T, Nothing> {
    val data: T

    override fun getTaskData() = data

    override fun getTaskError() = null
}

/**
 * Base Interface of Failed [TaskResult]
 */
sealed interface Failure<E> : TaskResult<Nothing, E> {
    val error: E

    override fun getTaskData() = null

    override fun getTaskError() = error
}

/**
 * Method to get the instance of SuccessFull [TaskResult]
 *
 * @param data: Data that will be outcome Of TaskResult
 */
fun <T> success(data: T): Success<T> {
    return SuccessImpl(data)
}

/**
 * Method to get the instance of Failed [TaskResult]
 *
 * @param error: error that happened as the outcome of TaskResult
 */
fun <E> failure(error: E): Failure<E> {
    return FailureImpl(error)
}

/**
 * Impl of [Success]
 *
 * @param data - SuccessFull data obtained from the task
 */
internal class SuccessImpl<T>(override val data: T) : Success<T>

/**
 * Impl of [Failure]
 *
 * @param error - Error obtained from the task
 */
internal class FailureImpl<E>(override val error: E) : Failure<E>

package com.newsandweather.test.common.flow

import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Helper class which help to test the values emitted by the FloW
 *
 * Will listen to the Flow and collect the values in Flow
 */
class TestFlowObserver<T>(
    scope: CoroutineScope,
    flow: Flow<T>
) {
    private val values by lazy { ArrayList<T>() }
    private val job: Job

    init {
        job = scope.launch {
            flow.collect {
                values.add(it)
            }
        }
    }

    /**
     * Assert if the collected value is Empty
     */
    fun assertIsEmpty(): TestFlowObserver<T> {
        Truth.assertThat(values).isEmpty()
        return this
    }

    /**
     * Assert if the collected value is matches escatly passed value in argument
     *
     * @param value against which the comparision happens
     */
    fun assertIsEmpty(vararg value: T): TestFlowObserver<T> {
        Truth.assertThat(this.values).containsExactly(value)
        return this
    }

    /**
     * Assert function where we pass the collected back to caller, where they can assert
     *
     * @param function - to which we pass the list of collected values
     */
    fun assertThat(function: (List<T>) -> Unit): TestFlowObserver<T> {
        function.invoke(values)
        return this
    }

    /**
     * Finish the observing job. after calling this will stop collecting the values
     */
    fun finish() = job.cancel()
}

/**
 * Create a [TestFlowObserver] which help to test the flow is working as expected
 */
fun <T> Flow<T>.getTestObserver(scope: CoroutineScope): TestFlowObserver<T> = TestFlowObserver(
    scope,
    this
)

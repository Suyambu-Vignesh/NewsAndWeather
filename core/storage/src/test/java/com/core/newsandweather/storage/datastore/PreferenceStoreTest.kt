package com.core.newsandweather.storage.datastore

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.newsandweather.test.common.flow.getTestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TestSuite for [PreferenceStore]
 */
@RunWith(AndroidJUnit4::class)
class PreferenceStoreTest {

    @Test
    fun `test For String Store return the default value when key is not present return the value if present and remove the values`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecord<String>(
            store,
            store::getAsString,
            store::putAsString,
            store::removeAsString,
            "StringKey",
            "default",
            "value"
        )
    }

    @Test
    fun `test For String Store Flow`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecordForFlow(
            "flowStringKey",
            "v1",
            store::getAsStringAsFlow,
            store::putAsString,
            store::removeAsString,
            listOf("v2", null, "v3"),
            listOf("v1", "v2", "v1", "v3")
        )
    }

    @Test
    fun `test For Int Store return the default value when key is not present return the value if present and remove the values`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecord<Int>(
            store,
            store::getAsInt,
            store::putAsInt,
            store::removeAsInt,
            "IntKey",
            -1,
            5
        )
    }

    @Test
    fun `test For Int Store Flow`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecordForFlow(
            "flowIntKey",
            1,
            store::getAsIntAsFlow,
            store::putAsInt,
            store::removeAsInt,
            listOf(2, null, 3, 3),
            listOf(1, 2, 1, 3)
        )
    }

    @Test
    fun `test For Boolean Store return the default value when key is not present return the value if present and remove the values`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecord<Boolean>(
            store,
            store::getAsBoolean,
            store::putAsBoolean,
            store::removeAsInt,
            "BoolKey",
            false,
            true
        )
    }

    @Test
    fun `test For Boolean Store Flow`() {
        val store = getDataStorePreference()
        putGetAndDeleteRecordForFlow(
            "flowBooleanKey",
            false,
            store::getAsBooleanAsFlow,
            store::putAsBoolean,
            store::removeAsBoolean,
            listOf(true, null, false),
            listOf(false, true, false, false)
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T> putGetAndDeleteRecord(
        store: PreferenceStore,
        getter: suspend (String, T) -> T,
        putter: suspend (String, T) -> Unit,
        remover: suspend (String) -> Unit,
        testKey: String,
        defaultValue: T,
        value: T
    ) {
        runTest {

            Truth.assertThat(getter.invoke(testKey, defaultValue))
                .isEqualTo(defaultValue)
            putter.invoke(testKey, value)
            Truth.assertThat(getter.invoke(testKey, defaultValue)).isEqualTo(value)
            remover.invoke(testKey)
            Truth.assertThat(getter.invoke(testKey, defaultValue))
                .isEqualTo(defaultValue)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T> putGetAndDeleteRecordForFlow(
        key: String,
        initialValue: T,
        getter: suspend (String, T) -> Flow<T>,
        putter: suspend (String, T) -> Unit,
        remover: suspend (String) -> Unit,
        valuesToBeEmitted: List<T?>,
        expectedValues: List<T?>
    ) {
        runTest {
            val testObserver = getter(key, initialValue).getTestObserver(this@runTest)
            this.runCurrent()

            valuesToBeEmitted.forEach { value ->
                if (value == null) {
                    remover.invoke(key)
                } else {
                    putter.invoke(key, value)
                }

                runCurrent()
            }

            testObserver.assertThat {
                Truth.assertThat(it.size).isEqualTo(expectedValues.size)
                for (index in expectedValues.indices) {
                    Truth.assertThat(it[index]).isEqualTo(expectedValues[index])
                }
            }.finish()
        }

    }

    private fun getDataStorePreference(): PreferenceStore {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return PreferenceStoreImpl(
            "testStore",
            context,
            PreferenceKeyStoreImpl()
        )
    }
}

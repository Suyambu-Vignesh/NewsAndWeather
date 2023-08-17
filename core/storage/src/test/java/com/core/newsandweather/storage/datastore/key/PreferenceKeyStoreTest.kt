package com.core.newsandweather.storage.datastore.key

import androidx.datastore.preferences.core.Preferences
import com.core.newsandweather.storage.datastore.PreferenceKeyStoreImpl
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Testsuite for [PreferenceKeyStore]
 */
class PreferenceKeyStoreTest {

    @Test
    fun `test stringPreferenceKeyMap creates new key if doesn't exist and return, and return the key if exist`() {
        createPreferenceKeyStore().run {
            verify(
                stringPreferenceKeyMap,
                ::getStringPreferenceKeyValue
            )
        }
    }

    @Test
    fun `test intPreferenceKeyMap creates new key if doesn't exist and return, and return the key if exist`() {
        createPreferenceKeyStore().run {
            verify(
                intPreferenceKeyMap,
                ::getIntPreferenceKeyValue
            )
        }
    }

    @Test
    fun `test booleanPreferenceKeyMap creates new key if doesn't exist and return, and return the key if exist`() {
        createPreferenceKeyStore().run {
            verify(
                booleanPreferenceKeyMap,
                ::getBooleanPreferenceKeyValue
            )
        }
    }

    private fun <T> verify(
        stringPreferenceKeyMap: HashMap<String, Preferences.Key<T>>,
        getter: (key: String) -> Preferences.Key<T>
    ) {
        val testKey = "TestKey"
        Truth.assertThat(stringPreferenceKeyMap.containsKey(testKey)).isFalse()

        val key = getter.invoke(testKey)
        Truth.assertThat(stringPreferenceKeyMap.containsKey(testKey)).isTrue()

        Truth.assertThat(getter.invoke(testKey)).isEqualTo(key)
    }

    private fun createPreferenceKeyStore(): PreferenceKeyStoreImpl {
        return PreferenceKeyStoreImpl()
    }
}
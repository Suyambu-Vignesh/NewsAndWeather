package com.core.newsandweather.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

internal class PreferenceStoreImpl @Inject constructor(
    storeName: String,
    private val context: Context,
    private val preferenceKeyStore: PreferenceKeyStore
) : PreferenceStore, PreferenceKeyStore by preferenceKeyStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        storeName
    )

    override suspend fun getAsBoolean(key: String, defaultValue: Boolean): Boolean {
        return getBooleanPreferenceKeyValue(key).getValue(defaultValue)
    }

    override fun getAsBooleanAsFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
        return getBooleanPreferenceKeyValue(key).getValueAsFlow(defaultValue)
    }

    override suspend fun putAsBoolean(key: String, value: Boolean) {
        getBooleanPreferenceKeyValue(key).putValue(value)
    }

    override suspend fun removeAsBoolean(key: String) {
        getBooleanPreferenceKeyValue(key).remove()
    }

    override suspend fun getAsInt(key: String, defaultValue: Int): Int {
        return getIntPreferenceKeyValue(key).getValue(defaultValue)
    }

    override fun getAsIntAsFlow(key: String, defaultValue: Int): Flow<Int> {
        return getIntPreferenceKeyValue(key).getValueAsFlow(defaultValue)
    }

    override suspend fun putAsInt(key: String, value: Int) {
        getIntPreferenceKeyValue(key).putValue(value)
    }

    override suspend fun removeAsInt(key: String) {
        getIntPreferenceKeyValue(key).remove()
    }

    override suspend fun getAllValuesAsString(): List<String> {
        var result: List<String>? = null
        try {
            context.dataStore.edit {
                result = it.asMap().values.filterIsInstance<String>()
            }
        } catch (exe: IOException) {
            //todo Log
        }

        return result?: emptyList()
    }

    override suspend fun getAsString(key: String, defaultValue: String): String {
        return getStringPreferenceKeyValue(key).getValue(defaultValue)
    }

    override fun getAsStringAsFlow(key: String, defaultValue: String): Flow<String> {
        return getStringPreferenceKeyValue(key).getValueAsFlow(defaultValue)
    }

    override suspend fun putAsString(key: String, value: String) {
        getStringPreferenceKeyValue(key).putValue(value)
    }

    override suspend fun removeAsString(key: String) {
        return getStringPreferenceKeyValue(key).remove()
    }

    override suspend fun removeAll() {
        try {
            context.dataStore.edit {
                it.clear()
            }
        } catch (exe: IOException) {
            //todo log
        }
    }

    private suspend fun <T> Preferences.Key<T>.putValue(value: T) {
        try {
            context.dataStore.edit { settings ->
                settings[this] = value
            }
        } catch (exe: IOException) {
            //todo log
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <K, V> Preferences.Key<K>.getValueAsFlow(defaultValue: V): Flow<V> {
        return context.dataStore.data.map { preferences ->
            preferences[this] as? V ?: defaultValue
        }.catch {
            //todo catch the value
            emit(defaultValue)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun <K, V> Preferences.Key<K>.getValue(defaultValue: V): V {
        return withContext(IO) {
            try {
                context.dataStore.data.firstOrNull()?.get(this@getValue) as? V ?: defaultValue
            } catch (exe: IOException) {
                //todo catch the value
                defaultValue
            }
        }
    }

    private suspend fun <T> Preferences.Key<T>.remove() {
        context.dataStore.edit {
            it.remove(this)
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

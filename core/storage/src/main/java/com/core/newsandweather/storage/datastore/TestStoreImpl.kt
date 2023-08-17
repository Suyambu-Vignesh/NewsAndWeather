package com.core.newsandweather.storage.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TestStoreImpl : PreferenceStore {
    override fun getAsStringAsFlow(key: String, defaultValue: String): Flow<String> {
        return flow { }
    }

    override suspend fun getAsString(key: String, defaultValue: String): String {
        return ""
    }

    override suspend fun putAsString(key: String, value: String) {
    }

    override suspend fun removeAsString(key: String) {
    }

    override suspend fun removeAll() {
        TODO("Not yet implemented")
    }

    override fun getAsIntAsFlow(key: String, defaultValue: Int): Flow<Int> {
        return flow { }
    }

    override suspend fun getAsInt(key: String, defaultValue: Int): Int {
        return 0
    }

    override suspend fun putAsInt(key: String, value: Int) {
    }

    override suspend fun removeAsInt(key: String) {
    }

    override suspend fun getAllValuesAsString(): List<String> {
        return emptyList()
    }

    override fun getAsBooleanAsFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
        return flow { }
    }

    override suspend fun getAsBoolean(key: String, defaultValue: Boolean): Boolean {
        return false
    }

    override suspend fun putAsBoolean(key: String, value: Boolean) {
    }

    override suspend fun removeAsBoolean(key: String) {
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
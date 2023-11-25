package com.core.newsandweather.storage.datastore

import androidx.annotation.VisibleForTesting
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

class PreferenceKeyStoreImpl : PreferenceKeyStore {
    @VisibleForTesting
    internal val stringPreferenceKeyMap by lazy { HashMap<String, Preferences.Key<String>>() }
    @VisibleForTesting
    internal val intPreferenceKeyMap by lazy { HashMap<String, Preferences.Key<Int>>() }
    @VisibleForTesting
    internal val booleanPreferenceKeyMap by lazy { HashMap<String, Preferences.Key<Boolean>>() }

    override fun getStringPreferenceKeyValue(key: String): Preferences.Key<String> {
        return keyFrom(
            stringPreferenceKeyMap,
            key
        ) {
            stringPreferencesKey(key)
        }
    }

    override fun getIntPreferenceKeyValue(key: String): Preferences.Key<Int> {
        return keyFrom(
            intPreferenceKeyMap,
            key
        ) {
            intPreferencesKey(key)
        }
    }

    override fun getBooleanPreferenceKeyValue(key: String): Preferences.Key<Boolean> {
        return keyFrom(
            booleanPreferenceKeyMap,
            key
        ) {
            booleanPreferencesKey(key)
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }

    private fun <T> keyFrom(
        prefKeyMap: MutableMap<String, Preferences.Key<T>>,
        key: String,
        defaultImp: (String) -> Preferences.Key<T>
    ): Preferences.Key<T> {
        return prefKeyMap[key] ?: defaultImp(key).also { prefKeyMap[key] = it }
    }
}
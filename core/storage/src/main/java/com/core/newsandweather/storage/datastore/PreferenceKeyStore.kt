package com.core.newsandweather.storage.datastore

import androidx.datastore.preferences.core.Preferences

interface PreferenceKeyStore {
    fun getStringPreferenceKeyValue(key: String): Preferences.Key<String>

    fun getIntPreferenceKeyValue(key: String): Preferences.Key<Int>

    fun getBooleanPreferenceKeyValue(key: String): Preferences.Key<Boolean>
}
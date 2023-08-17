package com.core.newsandweather.storage.datastore

import kotlinx.coroutines.flow.Flow

sealed interface PreferenceStore {
    /**
     * Suspended Method which Get the Value if present as [Boolean], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be return if there is no value for the flow
     *
     * @return value as [Boolean]
     */
    suspend fun getAsBoolean(key: String, defaultValue: Boolean): Boolean

    /**
     * Method which Get the Value if present as [Flow] of [Boolean], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be emitted in flow if there is no value for the flow
     *
     * @return [Flow] of [Boolean]
     */
    fun getAsBooleanAsFlow(key: String, defaultValue: Boolean): Flow<Boolean>

    /**
     * Suspended Method which Put/replace the value for the key in data store
     *
     * @param key - The key for which value need to be placed in data store
     * @param value - The value which will be placed for the key in data store
     */
    suspend fun putAsBoolean(key: String, value: Boolean)

    /**
     * Suspended Method which remove the entry in data store if present
     *
     * @param key - The key for which the entry need to removed if present
     */
    suspend fun removeAsBoolean(key: String)

    /**
     * Suspended Method which Get the Value if present as [Int], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be return if there is no value for the flow
     *
     * @return value as [Int]
     */
    suspend fun getAsInt(key: String, defaultValue: Int): Int

    /**
     * Method which Get the Value if present as [Flow] of [Int], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be emitted in flow if there is no value for the flow
     *
     * @return [Flow] of [Int]
     */
    fun getAsIntAsFlow(key: String, defaultValue: Int): Flow<Int>

    /**
     * Suspended Method which Put/replace the value for the key in data store
     *
     * @param key - The key for which value need to be placed in data store
     * @param value - The value which will be placed for the key in data store
     */
    suspend fun putAsInt(key: String, value: Int)

    /**
     * Suspended Method which remove the entry in data store if present
     *
     * @param key - The key for which the entry need to removed if present
     */
    suspend fun removeAsInt(key: String)

    /**
     * Suspended Method which will get all the String Values in the Store. If No values is present
     * will return empty string
     *
     * @return [List] of [String]
     */
    suspend fun getAllValuesAsString(): List<String>

    /**
     * Suspended Method which Get the Value if present as [String], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be return if there is no value for the flow
     *
     * @return value as [String]
     */
    suspend fun getAsString(key: String, defaultValue: String): String

    /**
     * Method which Get the Value if present as [Flow] of [String], or return the Flow of default Value
     *
     * @param key - The key for which value need to be looked in data store
     * @param defaultValue - The Value which will be emitted in flow if there is no value for the flow
     *
     * @return [Flow] of [String]
     */
    fun getAsStringAsFlow(key: String, defaultValue: String): Flow<String>


    /**
     * Suspended Method which Put/replace the value for the key in data store
     *
     * @param key - The key for which value need to be placed in data store
     * @param value - The value which will be placed for the key in data store
     */
    suspend fun putAsString(key: String, value: String)

    /**
     * Suspended Method which remove the entry in data store if present
     *
     * @param key - The key for which the entry need to removed if present
     */
    suspend fun removeAsString(key: String)

    /**
     * Suspended Method which help to clear the data store
     */
    suspend fun removeAll()
}
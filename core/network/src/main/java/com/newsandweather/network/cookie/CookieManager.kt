package com.newsandweather.network.cookie

import androidx.annotation.VisibleForTesting
import com.core.newsandweather.storage.datastore.PreferenceStore
import com.core.newsandweather.storage.init.CookieStore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.net.HttpCookie
import java.net.URI
import javax.inject.Inject
import java.net.CookieStore as AndroidCookieStore

class CoreCookieStore @Inject constructor(
    @CookieStore private val preferenceStore: PreferenceStore,
    private val inMemoryCookieStore: AndroidCookieStore,
    private val lock: Mutex
) : AndroidCookieStore {
    private var isInitialized = false

    @VisibleForTesting
    internal suspend fun setup() {
        lock.withLock {
            if (isInitialized) {
                return
            }

            inMemoryCookieStore.removeAll()

            val list = preferenceStore.getAllValuesAsString()

            for (encodedCookie in list) {
                val cookie = SerializableCookie.createSerializableCookie(encodedCookie)

                cookie?.let {
                    if (!it.hasExpired()) {
                        inMemoryCookieStore.add(null, it.getHttpCookies())
                    } else {
                        preferenceStore.removeAsString(it.getCookieKey())
                    }
                }
            }

            isInitialized = true
        }
    }

    private suspend fun saveCookie(uri: URI, cookie: HttpCookie?) {
        if (cookie == null) {
            return
        }

        val serializableCookie = SerializableCookie.createSerializableCookie(cookie)
        val hasExpired = serializableCookie.hasExpired()
        val encodedString = serializableCookie.getEncodedString()
        val cookieKey = serializableCookie.getCookieKey()

        if (hasExpired || encodedString == null) {
            // cookie expired or cannot convert to encoded str. so will remove this.
            preferenceStore.removeAsString(cookieKey)
            inMemoryCookieStore.remove(uri, cookie)
        } else {
            preferenceStore.putAsString(cookieKey, encodedString)
            inMemoryCookieStore.add(uri, cookie)
        }
    }

    override fun add(uri: URI, cookie: HttpCookie?) = runBlocking {
        setup()
        saveCookie(uri, cookie)
    }

    override fun get(uri: URI): MutableList<HttpCookie> = runBlocking {
        setup()
        inMemoryCookieStore.get(uri)
    }

    override fun getCookies(): List<HttpCookie> = runBlocking {
        setup()
        inMemoryCookieStore.cookies
    }

    override fun getURIs(): MutableList<URI> = runBlocking {
        setup()
        inMemoryCookieStore.urIs
    }

    override fun remove(uri: URI?, cookie: HttpCookie?): Boolean = runBlocking {
        setup()
        val value = cookie?.let {
            inMemoryCookieStore.remove(uri, cookie)
            preferenceStore.removeAsString(cookie.getCookieKey())
            true
        } ?: false

        value
    }

    override fun removeAll(): Boolean = runBlocking {
        setup()
        preferenceStore.removeAll()
        inMemoryCookieStore.removeAll()
    }
}
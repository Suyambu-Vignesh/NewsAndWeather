package com.newsandweather.network.cookie

import com.core.newsandweather.storage.datastore.PreferenceStore
import com.core.newsandweather.storage.init.CookieStore
import com.google.common.truth.Truth
import com.newsandweather.network.cookie.CoreCookieStore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coVerify
import io.mockk.impl.annotations.SpyK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.CookieManager
import java.net.HttpCookie
import java.net.URI
import javax.inject.Inject

/**
 * Testsuite for [CookieManager]
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CookieManagerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @CookieStore
    @SpyK
    lateinit var dataStore: PreferenceStore

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `test addCookie into datastore with null cookie`() = runTest {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        assertAddCookie(
            inMemoryStore,
            null
        ) {
            verify(exactly = 0) { inMemoryStore.remove(any(), any()) }
            verify(exactly = 0) { inMemoryStore.add(any(), any()) }

            coVerify(exactly = 0) { dataStore.removeAsString(any()) }
            coVerify(exactly = 0) { dataStore.putAsString(any(), any()) }
        }
    }

    @Test
    fun `test addCookie into datastore with non-null and un-expired cookie`() = runTest {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val cookie = HttpCookie("cookieName1", "cookieValue1")
        cookie.maxAge = 120
        assertAddCookie(
            inMemoryStore,
            cookie
        ) {
            verify(exactly = 0) { inMemoryStore.remove(any(), any()) }
            verify(exactly = 1) { inMemoryStore.add(any(), any()) }

            coVerify(exactly = 0) { dataStore.removeAsString(any()) }
            coVerify(exactly = 1) { dataStore.putAsString(any(), any()) }
        }
    }

    @Test
    fun `test addCookie into datastore with non-null and expired cookie`() = runTest {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val cookie = HttpCookie("cookieName1", "cookieValue1")
        cookie.maxAge = -1
        assertAddCookie(
            inMemoryStore,
            cookie
        ) {
            verify(exactly = 1) { inMemoryStore.remove(any(), any()) }
            verify(exactly = 0) { inMemoryStore.add(any(), any()) }

            coVerify(exactly = 1) { dataStore.removeAsString(any()) }
            coVerify(exactly = 0) { dataStore.putAsString(any(), any()) }
        }
    }

    private fun assertAddCookie(
        inMemoryStore: java.net.CookieStore,
        httpCookie: HttpCookie?,
        assertCallBack: () -> Unit
    ) {
        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        cookie.add(URI("http://nourl.com"), httpCookie)

        assertCallBack.invoke()
    }

    @Test
    fun `test get Cookies when there is no cookie`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)

        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        Truth.assertThat(cookie.get(URI("http://nourl.com"))).isEmpty()
    }

    @Test
    fun `test get Cookies when there is there is 2 cookie`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val uri = URI("http://nourl.com")

        val httpCookie1 = HttpCookie("httpCookie1", "v1")
        httpCookie1.maxAge = 120
        val httpCookie2 = HttpCookie("httpCookie2", "v2")
        httpCookie2.maxAge = 120

        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        cookie.add(uri, httpCookie1)
        cookie.add(uri, httpCookie2)

        Truth.assertThat(cookie.get(uri).size).isEqualTo(2)
    }

    @Test
    fun `test get Cookies when there is there is 2 un-expired cookie and expired cookie`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val uri = URI("http://nourl.com")

        val httpCookie1 = HttpCookie("httpCookie1", "v1")
        httpCookie1.maxAge = 120
        val httpCookie2 = HttpCookie("httpCookie2", "v2")
        httpCookie2.maxAge = 120
        val httpCookie3 = HttpCookie("httpCookie3", "v3")
        httpCookie3.maxAge = -1

        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        cookie.add(uri, httpCookie1)
        cookie.add(uri, httpCookie2)
        cookie.add(uri, httpCookie3)

        Truth.assertThat(cookie.cookies.size).isEqualTo(2)
        Truth.assertThat(cookie.urIs.size).isEqualTo(1)
    }

    @Test
    fun `test get remove with null cookie`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        Truth.assertThat(cookie.remove(null, null)).isFalse()

        verify(exactly = 0) { inMemoryStore.remove(any(), any()) }
        coVerify(exactly = 0) { dataStore.removeAsString(any()) }
    }

    @Test
    fun `test remove cookie`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )
        val uri = URI("http://nourl.com")

        val httpCookie1 = HttpCookie("httpCookie1", "v1")
        httpCookie1.maxAge = 120

        cookie.add(uri, httpCookie1)

        Truth.assertThat(cookie.remove(uri, httpCookie1)).isTrue()

        verify(exactly = 1) { inMemoryStore.remove(any(), any()) }
        coVerify(exactly = 1) { dataStore.removeAsString(any()) }
    }

    @Test
    fun `test removeAll`() {
        val inMemoryStore = spyk(CookieManager().cookieStore)
        val cookie = CoreCookieStore(
            dataStore,
            inMemoryStore,
            Mutex()
        )

        val uri = URI("http://nourl.com")

        val httpCookie1 = HttpCookie("httpCookie1", "v1")
        httpCookie1.maxAge = 120

        cookie.add(uri, httpCookie1)

        verify(exactly = 1) { inMemoryStore.removeAll() }

        cookie.removeAll()

        coVerify(exactly = 2) { dataStore.removeAll() }
        verify(exactly = 2) { inMemoryStore.removeAll() }
    }
}

package com.core.newsandweather.storage.datastore.key

import com.core.newsandweather.storage.datastore.PreferenceStore
import com.core.newsandweather.storage.init.CookieStore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config
import java.net.CookieManager
import java.net.URI
import javax.inject.Inject

/**
 * Testsuite for [CookieManager]
 */
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class CookieManaerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @CookieStore
    lateinit var dataStore: PreferenceStore

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `test addCookie into datastore`() = runTest {

    }
}

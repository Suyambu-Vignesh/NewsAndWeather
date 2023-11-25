package com.core.newsandweather.storage.init.mock

import android.content.Context
import com.core.newsandweather.storage.datastore.PreferenceKeyStore
import com.core.newsandweather.storage.datastore.PreferenceStore
import com.core.newsandweather.storage.datastore.PreferenceStoreImpl
import com.core.newsandweather.storage.init.ConfigStore
import com.core.newsandweather.storage.init.CookieStore
import com.core.newsandweather.storage.init.PreferenceStoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.spyk
import javax.inject.Singleton

//todo move this to its own module.
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PreferenceStoreModule::class]
)
class MockPreferenceStoreModule {
    @Singleton
    @Provides
    @CookieStore
    fun providesCookiePreferenceStore(
        @ApplicationContext context: Context,
        preferenceKeyStore: PreferenceKeyStore
    ) : PreferenceStore {
        return spyk(PreferenceStoreImpl(STORE_COOKIE, context, preferenceKeyStore))
    }

    @Singleton
    @Provides
    @ConfigStore
    fun providesConfigPreferenceStore(
        @ActivityContext context: Context,
        preferenceKeyStore: PreferenceKeyStore
    ): PreferenceStore {
        return spyk(PreferenceStoreImpl(STORE_CONFIG, context, preferenceKeyStore))
    }

    companion object {
        private const val STORE_COOKIE: String = "cookie_store"
        private const val STORE_CONFIG: String = "config_store"
    }
}
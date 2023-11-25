package com.core.newsandweather.storage.init

import android.content.Context
import com.core.newsandweather.storage.datastore.PreferenceKeyStore
import com.core.newsandweather.storage.datastore.PreferenceStore
import com.core.newsandweather.storage.datastore.PreferenceStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceStoreModule {

    @Singleton
    @Provides
    @CookieStore
    fun providesCookiePreferenceStore(
        @ApplicationContext context: Context,
        preferenceKeyStore: PreferenceKeyStore
    ) : PreferenceStore {
        return PreferenceStoreImpl(STORE_COOKIE, context, preferenceKeyStore)
    }

    @Singleton
    @Provides
    @ConfigStore
    fun providesConfigPreferenceStore(
        @ActivityContext context: Context,
        preferenceKeyStore: PreferenceKeyStore
    ): PreferenceStore {
        return PreferenceStoreImpl(STORE_CONFIG, context, preferenceKeyStore)
    }

    companion object {
        private const val STORE_COOKIE: String = "cookie_store"
        private const val STORE_CONFIG: String = "config_store"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CookieStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConfigStore
package com.newsandweather.network.init

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.sync.Mutex
import java.net.CookieManager
import java.net.CookieStore as AndroidCookieStore

@Module
@InstallIn(SingletonComponent::class)
class CookieStoreModule {

    @Provides
    fun provideAndroidCookieStore(): AndroidCookieStore {
        return CookieManager().cookieStore
    }

    @Provides
    fun provideMutex(): Mutex {
        return Mutex()
    }
}
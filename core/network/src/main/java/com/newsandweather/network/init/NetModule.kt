package com.newsandweather.network.init

import com.newsandweather.network.cookie.CoreCookieStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.sync.Mutex
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    companion object {
        const val NEWS_API_BASE_URL = "https://newsapi.org"

        //MY_KEY="b7122b5c5f8948eda9715867b6240ce6"
        //MY_URL="https://newsapi.org"
    }

    @Provides
    fun getAndroidCookieStore(): CookieStore {
        return CookieManager().cookieStore
    }

    @Provides
    fun getMutex(): Mutex {
        return Mutex()
    }

    @Provides
    fun getCookieManager(cookieStore: CoreCookieStore): CookieManager {
        return CookieManager(cookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER)
    }

    @Provides
    fun providesOkHttpClient(
        cookieManager: CookieManager,
        interceptors: List<Interceptor>
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(cookieManager))

        interceptors.forEach {
            builder.addInterceptor(it)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    //@NewsApi
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NEWS_API_BASE_URL)
            .client(okHttpClient)
            .build()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApi

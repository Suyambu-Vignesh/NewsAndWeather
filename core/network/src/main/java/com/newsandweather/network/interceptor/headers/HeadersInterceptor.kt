package com.newsandweather.network.interceptor.headers

import android.content.Context
import android.provider.Settings
import com.newsandweather.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * [okhttp3]'s [Interceptor] to add common header across every request
 */
class HeadersInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        builder.addHeader(HEADER_KEY_APP_VERSION, BuildConfig.APP_VERSION_NAME)
        builder.addHeader(HEADER_KEY_APP_PLATFORM, HEADER_VALUE_APP_PLATFORM)
        builder.addHeader(HEADER_KEY_DEVICE_ID, Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))

        return chain.proceed(builder.build())
    }

    companion object {
        const val HEADER_KEY_APP_VERSION = "HEADER_APP_VERSION"
        const val HEADER_KEY_APP_PLATFORM = "HEADER_APP_PLATFORM"
        const val HEADER_KEY_DEVICE_ID = "HEADER_DEVICE_ID"
        const val HEADER_KEY_CUSTOMER_ID = "HEADER_CUSTOMER_ID" // todo once auth established
        const val HEADER_KEY_APP_SESSION_ID = "HEADER_APP_SESSION_ID"
        const val HEADER_KEY_CLIENT_TRACE_ID = "HEADER_CLIENT_TRACE_ID"

        const val HEADER_VALUE_APP_PLATFORM = "ANDROID"
    }
}
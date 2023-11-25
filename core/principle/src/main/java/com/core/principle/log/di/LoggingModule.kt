package com.core.principle.log.di

import com.core.principle.BuildConfig
import com.core.principle.log.api.AppLogger
import com.core.principle.log.impl.AppDebugLoggerImpl
import com.core.principle.log.impl.AppProdLoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoggingModule {
    /**
     * Provides the Impl of AppLogger based on the Debug or release
     *
     * @return if Debug build will return [AppDebugLoggerImpl] else [AppProdLoggerImpl]
     */
    @Singleton
    @Provides
    fun provideLogger(): AppLogger {
        return if (BuildConfig.DEBUG) {
            AppDebugLoggerImpl()
        } else {
            AppProdLoggerImpl()
        }
    }
}

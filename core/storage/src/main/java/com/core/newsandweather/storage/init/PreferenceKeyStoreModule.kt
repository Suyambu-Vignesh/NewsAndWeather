package com.core.newsandweather.storage.init

import com.core.newsandweather.storage.datastore.PreferenceKeyStore
import com.core.newsandweather.storage.datastore.PreferenceKeyStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PreferenceKeyStoreModule {
    @Provides
    fun providesPreferenceKeyStore(): PreferenceKeyStore = PreferenceKeyStoreImpl()
}
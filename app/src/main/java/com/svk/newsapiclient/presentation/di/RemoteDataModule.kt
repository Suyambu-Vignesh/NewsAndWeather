package com.svk.newsapiclient.presentation.di

import com.svk.newsapiclient.data.api.NewsAPIService
import com.svk.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.svk.newsapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ):NewsRemoteDataSource{
       return NewsRemoteDataSourceImpl(newsAPIService)
    }

}













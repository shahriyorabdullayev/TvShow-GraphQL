package com.shahriyor.android_imperativ_graphql.di

import com.shahriyor.android_imperativ_graphql.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Apollo Related
     */
    @Provides
    @Singleton
    fun tvShowService() = ApiClient()








}
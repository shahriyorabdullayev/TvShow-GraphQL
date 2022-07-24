package com.shahriyor.android_imperativ_graphql.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class ApiClient {

    private fun server(): String {
        if (Server.IS_TESTER) return Server.SERVER_DEVELOPMENT
        return Server.SERVER_PRODUCTION
    }

    private val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }


    fun get(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(server())
            .okHttpClient(httpClient)
            .build()
    }
}
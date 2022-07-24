package com.shahriyor.android_imperativ_graphql.repository

import com.shahriyor.android_graphql.PopularMoviesQuery
import com.shahriyor.android_imperativ_graphql.data.network.ApiClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiClient: ApiClient,
) {

    suspend fun popularMovies() = apiClient.get().query(PopularMoviesQuery()).execute()

}
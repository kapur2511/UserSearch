package com.testing.githubsearch.data.api

import com.testing.githubsearch.data.remotemodels.GitUsersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


private const val SEARCH_API = "/search/users"
private const val PER_PAGE_RESULTS = 100

interface SearchApi {

    @GET(SEARCH_API)
    suspend fun searchUsers(
        @Query("q")
        searchText: String,
        @Query("per_page")
        perPage: Int = PER_PAGE_RESULTS
    ): Response<GitUsersResponseDTO>
}
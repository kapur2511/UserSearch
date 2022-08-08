package com.testing.githubsearch.data.datasource

import com.testing.githubsearch.data.remotemodels.GitUsersResponseDTO
import com.testing.githubsearch.util.ResponseWrapper
import com.testing.githubsearch.util.ResultWrapper

interface SearchDatasource {

    suspend fun fetchUsers(searchText: String): ResponseWrapper<GitUsersResponseDTO>
}
package com.testing.githubsearch.data.repositories

import com.testing.githubsearch.data.domain.GitUser
import com.testing.githubsearch.util.ResultWrapper

interface SearchRepository {

    suspend fun fetchUsers(searchText: String): ResultWrapper<List<GitUser>>
}
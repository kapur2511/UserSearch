package com.testing.githubsearch.data.repositories

import com.testing.githubsearch.data.datasource.SearchDatasource
import com.testing.githubsearch.domain.GitUser
import com.testing.githubsearch.util.*
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDatasource: SearchDatasource
): SearchRepository {

    override suspend fun fetchUsers(
        searchText: String
    ): ResultWrapper<List<GitUser>> {
        return when (val response = searchDatasource.fetchUsers(searchText)) {
            is SuccessResponseState -> {
                val listOfUsers = response.data.items!!.map { gitUserDTO ->
                    GitUserDTOMapper.mapToGitUser(gitUserDTO = gitUserDTO)
                }
                SuccessState(listOfUsers)
            }
            is ErrorResponseState -> {
                ErrorState(response.throwable)
            }
        }
    }
}
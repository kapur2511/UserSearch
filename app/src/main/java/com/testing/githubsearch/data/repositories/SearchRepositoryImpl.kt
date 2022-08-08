package com.testing.githubsearch.data.repositories

import com.testing.githubsearch.data.datasource.SearchDatasource
import com.testing.githubsearch.data.domain.GitUser
import com.testing.githubsearch.util.ErrorState
import com.testing.githubsearch.util.GitUserDTOMapper
import com.testing.githubsearch.util.ResultWrapper
import com.testing.githubsearch.util.SuccessState
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDatasource: SearchDatasource
): SearchRepository {

    override suspend fun fetchUsers(
        searchText: String
    ): ResultWrapper<List<GitUser>> {
        val response = searchDatasource.fetchUsers(searchText)
        return if (response is SuccessState) {
            // items will be non-null.
            val listOfUsers = response.data.items!!.map { gitUserDTO ->
                GitUserDTOMapper.mapToGitUser(gitUserDTO = gitUserDTO)
            }
            SuccessState(listOfUsers)
        } else {
            ErrorState((response as? ErrorState)?.throwable)
        }
    }
}
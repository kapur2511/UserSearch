package com.testing.githubsearch.data.datasource

import android.util.Log
import com.testing.githubsearch.data.api.SearchApi
import com.testing.githubsearch.data.remotemodels.GitUsersResponseDTO
import com.testing.githubsearch.util.*
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
): SearchDatasource {

    override suspend fun fetchUsers(searchText: String): ResponseWrapper<GitUsersResponseDTO> {
        val response = searchApi.searchUsers(searchText = searchText)
        return if (
            response.isSuccessful &&
            response.body() != null &&
            response.body()!!.items.isNullOrEmpty().not()
        ) {
            // success case
            Log.d(TAG, "Success! ${response.body()}")
            SuccessResponseState(response.body()!!)
        } else {
            // error case
            val error = response.errorBody().toString()
            Log.d(TAG, "Error! $error == Code ${response.code()}")
            ErrorResponseState(Throwable(error))
        }
    }

    companion object {
        private const val TAG = "SearchDatasource"
    }
}
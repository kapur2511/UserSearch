package com.testing.githubsearch.data.datasource

import android.util.Log
import com.testing.githubsearch.data.api.SearchApi
import com.testing.githubsearch.data.remotemodels.GitUsersResponseDTO
import com.testing.githubsearch.util.ErrorState
import com.testing.githubsearch.util.ResultWrapper
import com.testing.githubsearch.util.SuccessState
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
): SearchDatasource {

    override suspend fun fetchUsers(searchText: String): ResultWrapper<GitUsersResponseDTO> {
        val response = searchApi.searchUsers(searchText = searchText)
        return if (
            response.isSuccessful &&
            response.body() != null &&
            response.body()!!.items.isNullOrEmpty().not()
        ) {
            // success case
            Log.d(TAG, "Success! ${response.body()}")
            SuccessState(response.body()!!)
        } else {
            // error case
            val error = response.errorBody().toString()
            Log.d(TAG, "Error! $error == Code ${response.code()}")
            ErrorState(Throwable(error))
        }
    }

    companion object {
        private const val TAG = "SearchDatasource"
    }
}
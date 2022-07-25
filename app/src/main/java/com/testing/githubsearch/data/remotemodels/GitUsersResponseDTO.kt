package com.testing.githubsearch.data.remotemodels

import com.google.gson.annotations.SerializedName
import com.testing.githubsearch.data.remotemodels.GitUserDTO

data class GitUsersResponseDTO(
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    @SerializedName("items")
    val items: List<GitUserDTO>?
)
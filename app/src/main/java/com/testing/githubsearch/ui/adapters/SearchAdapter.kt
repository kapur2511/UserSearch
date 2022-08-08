package com.testing.githubsearch.ui.adapters

import com.testing.githubsearch.domain.GitUser

class SearchAdapter(
    list: List<GitUser>
): BaseAdapter<GitUser>(list) {

    fun refreshDataset(list: List<GitUser>) {
        listOfModels = list
        // As search will most of the times have different results hence
        // using this. The old results are invalid which needs to be cleared
        // which is why we have used notifyDataSetChanged()
        notifyDataSetChanged()
    }
}
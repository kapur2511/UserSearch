package com.testing.githubsearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.testing.githubsearch.R
import com.testing.githubsearch.data.domain.BaseModel
import com.testing.githubsearch.data.domain.GitUser
import com.testing.githubsearch.ui.viewholders.BaseViewHolder
import com.testing.githubsearch.ui.viewholders.GitUserVH
import com.testing.githubsearch.ui.viewholders.UnknownVH

open class BaseAdapter<T: BaseModel>(
    var listOfModels: List<BaseModel>
): RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int
    ): BaseViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            USER_VIEW -> GitUserVH(view)
            else -> UnknownVH(view)
        } as BaseViewHolder<T>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(model = listOfModels[position] as T)
    }

    override fun getItemCount(): Int = listOfModels.size

    override fun getItemViewType(position: Int): Int {
        return when (listOfModels[position]) {
            is GitUser -> USER_VIEW
            else -> UNKNOWN_VIEW
        }
    }

    companion object {
        const val USER_VIEW = R.layout.user_view
        const val UNKNOWN_VIEW = -1
    }
}
package com.testing.githubsearch.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.testing.githubsearch.domain.BaseModel

abstract class BaseViewHolder<T: BaseModel>(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bind(model: T)
}
package com.testing.githubsearch.ui.viewholders

import android.view.View
import com.testing.githubsearch.data.domain.BaseModel
import com.testing.githubsearch.ui.viewholders.BaseViewHolder

class UnknownVH(view: View): BaseViewHolder<BaseModel>(view) {

    override fun bind(model: BaseModel) {
        /*no-op*/
    }
}
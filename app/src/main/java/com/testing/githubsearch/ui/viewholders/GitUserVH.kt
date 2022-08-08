package com.testing.githubsearch.ui.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.testing.githubsearch.domain.GitUser
import com.testing.githubsearch.databinding.UserViewBinding

class GitUserVH(itemView: View): BaseViewHolder<GitUser>(itemView) {

    override fun bind(model: GitUser) {
        UserViewBinding.bind(itemView).apply {
            tvName.text = model.login
            tvScore.text = "Score: ${model.score}"
            tvType.text = model.type
            Glide.with(ivAvatar.context).load(model.avatarUrl).into(ivAvatar)
        }
    }
}
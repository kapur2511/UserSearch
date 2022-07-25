package com.testing.githubsearch.ui.decorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SpaceDecorator(
    context: Context,
    @DimenRes itemOffsetId: Int
): RecyclerView.ItemDecoration() {

    private val itemOffset = context.resources.getDimensionPixelSize(itemOffsetId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(itemOffset, itemOffset, itemOffset, itemOffset)
    }
}
package com.gitwho.common

import android.view.View

/**
 * A Generic interface that is used to handle recyclerview item on click.
 */
interface OnRecyclerItemClickCallback<T> {
    fun onRecyclerItemClicked(position:Int, view:View, data:T)
}
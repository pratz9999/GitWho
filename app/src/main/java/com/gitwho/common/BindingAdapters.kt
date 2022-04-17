package com.gitwho.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gitwho.R

@BindingAdapter("url")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .apply(RequestOptions().centerCrop())
        .into(view)
}

@BindingAdapter("profile")
fun loadProfile(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url ?: "")
        .apply(RequestOptions().centerCrop())
        .circleCrop()
        .placeholder(R.drawable.ic_app)
        .into(view)
}
package com.john.flickr.search.view

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter(value = ["appCompat:url", "appCompat:thumbnail"])
fun View.setImageResource(imageView: AppCompatImageView, url: String, thumbnail: Boolean) {
    Glide.with(context).asDrawable().centerCrop()
        .load(url)
        .thumbnail(if (thumbnail) Glide.with(context).load(url) else null)
        .into(imageView)
}
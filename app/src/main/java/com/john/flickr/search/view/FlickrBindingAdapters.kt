package com.john.flickr.search.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.john.flickr.data.AppConstant.Companion.SQUARE_THUMB_SIZE


@BindingAdapter(value = ["appCompat:url", "appCompat:thumbnail"])
fun View.setImageResource(imageView: AppCompatImageView, url: String, thumbnail: Boolean) {
    Glide.with(context).asDrawable().centerCrop()
        .load(url)
        .thumbnail(if (thumbnail) Glide.with(context).load(url) else null)
        .into(imageView)
}

@BindingAdapter("app:url")
fun View.setImageResource(imageView: ImageView, url: String) {
    Glide.with(context).asDrawable().centerCrop().placeholder(ColorDrawable(Color.GRAY))
        .load(url)
        .thumbnail(
            Glide.with(context).asDrawable()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(SQUARE_THUMB_SIZE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .load(url)
        )
        .into(imageView)
}
package com.john.flickr.search.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.john.flickr.data.AppConstant.Companion.SQUARE_THUMB_SIZE

object FlickrBindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["android:src", "app:thumbnail"], requireAll = true)
    fun setImageUrl(
        imageView: AppCompatImageView,
        thumbnail: Boolean,
        url: String
    ) {
        Glide.with(imageView.context).asDrawable().centerCrop()
            .load(url)
            .thumbnail(if (thumbnail) Glide.with(imageView.context).load(url) else null)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, src: String) {
        Glide.with(imageView.context).asDrawable().centerCrop()
            .placeholder(ColorDrawable(Color.GRAY))
            .load(src)
            .thumbnail(
                Glide.with(imageView.context).asDrawable()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .override(SQUARE_THUMB_SIZE)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .load(src)
            )
            .into(imageView)
    }
}


package com.john.flickr.search.view.details

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object FlickrDetailsBindingAdapter
{
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResourceUrl(imageView: ImageView, src: String) {
        Glide.with(imageView.context)
            .load(src)
            .apply(RequestOptions.fitCenterTransform())
            .into(imageView)
    }
}
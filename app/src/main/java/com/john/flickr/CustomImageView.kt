package com.john.flickr

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.john.flickr.data.AppConstant

class CustomImageView : ImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

//    fun setSrc(src: String) {
//        Glide.with(context).asDrawable().centerCrop().placeholder(ColorDrawable(Color.GRAY))
//            .load(src)
//            .thumbnail(
//                Glide.with(context).asDrawable()
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .override(AppConstant.SQUARE_THUMB_SIZE)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .load(src)
//            )
//            .into(this)
//    }
}
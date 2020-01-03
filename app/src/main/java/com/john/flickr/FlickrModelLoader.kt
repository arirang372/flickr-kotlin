package com.john.flickr

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import com.john.flickr.data.AppConstant
import com.john.flickr.data.AppConstant.Companion.getPhotoURL
import com.john.flickr.search.model.Photo
import java.io.InputStream

class FlickrModelLoader : BaseGlideUrlLoader<Photo> {
    class Factory : ModelLoaderFactory<Photo, InputStream> {
        private val modelCache = ModelCache<Photo, GlideUrl>(500)
        lateinit var modelLoader: FlickrModelLoader
//        init {
//               modelLoader  = FlickrModelLoader(
//                multiFactory.build(GlideUrl::class.java, InputStream::class.java),
//                modelCache
//            )
//        }

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<Photo, InputStream> {
            modelLoader = FlickrModelLoader(
                multiFactory.build(GlideUrl::class.java, InputStream::class.java),
                modelCache
            )
            return modelLoader
        }

        override fun teardown() {
        }
    }

    lateinit var photoUrl: String

    internal constructor(
        urlLoader: ModelLoader<GlideUrl, InputStream>,
        modelCache: ModelCache<Photo, GlideUrl>
    ) : super(urlLoader, modelCache)

    override fun getAlternateUrls(
        model: Photo?,
        width: Int,
        height: Int,
        options: Options?
    ): List<String> {
        return AppConstant.getAlternateUrls(photo = model, width = width, height = height)
    }

    override fun getUrl(model: Photo?, width: Int, height: Int, options: Options?): String {
        photoUrl = getPhotoURL(model, width, height)
        return photoUrl
    }

    override fun handles(model: Photo): Boolean {
        return true
    }

    fun getUrl(): String {
        return photoUrl
    }

}
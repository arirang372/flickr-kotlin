package com.john.flickr.data.source.remote

import com.john.flickr.data.FlickrResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
    @GET("/services/rest/")
    fun getPhotoContents(
        @Query("method") method: String,
        @Query("per_page") perPage: Int,
        @Query("format") format: String,
        @Query("api_key") apiKey: String,
        @Query("text") text: String
    ): Observable<FlickrResponse>
}
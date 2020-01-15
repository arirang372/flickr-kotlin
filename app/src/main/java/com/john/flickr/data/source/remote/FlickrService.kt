package com.john.flickr.data.source.remote

import com.john.flickr.data.FlickrResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=300&format=json&api_key=f0e6fbb5fdf1f3842294a1d21f84e8a6&text=kitten
 * How to fix the json parse issue below
 * https://stackoverflow.com/questions/27067970/gson-parsing-with-retrofit-parsing-flickr-json-response
 *
 */

interface FlickrService {
    @GET("/services/rest/")
    fun getPhotoContents(
        @Query("method") method: String,
        @Query("per_page") perPage: Int,
        @Query("format") format: String,
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ): Observable<FlickrResponse>
}
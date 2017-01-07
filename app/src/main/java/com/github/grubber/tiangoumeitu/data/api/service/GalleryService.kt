package com.github.grubber.tiangoumeitu.data.api.service

import com.github.grubber.tiangoumeitu.data.api.model.CommonResponse
import com.github.grubber.tiangoumeitu.data.api.model.Gallery
import com.github.grubber.tiangoumeitu.data.api.model.PictureList
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by grubber on 2017/1/7.
 */
interface GalleryService {
    @GET("news")
    fun getGalleries(): Observable<CommonResponse<Gallery>>

    @GET("show")
    fun getPictureList(@Query("id") id: Int): Observable<PictureList>
}
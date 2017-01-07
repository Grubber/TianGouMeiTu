package com.github.grubber.tiangoumeitu.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by grubber on 2017/1/7.
 */
data class Gallery(
        val id: Int,
        @SerializedName("galleryclass") val galleryClass: Int,
        val title: String,
        val img: String,
        val count: Int,
        @SerializedName("rcount") val readCount: Int,
        @SerializedName("fcount") val favorCount: Int,
        val size: Int
)
package com.github.grubber.tiangoumeitu.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by grubber on 2017/1/7.
 */
data class CommonResponse<T>(
        val status: Boolean,
        val total: Long,
        @SerializedName("tngou") val data: List<T>
)
package com.github.grubber.tiangoumeitu.data.exception

import com.google.gson.annotations.SerializedName

/**
 * Created by grubber on 2017/1/7.
 */
data class ApiError(
        var success: Boolean,
        @SerializedName("error_msg") var msg: String
)
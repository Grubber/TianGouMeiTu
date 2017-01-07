package com.github.grubber.tiangoumeitu.data.api.model

/**
 * Created by grubber on 2017/1/7.
 */
data class Picture(val src: String)

data class PictureList(val status: Boolean, val list: List<Picture>)
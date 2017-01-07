package com.github.grubber.tiangoumeitu.util

import android.net.Uri
import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import javax.inject.Inject

/**
 * Created by grubber on 2017/1/7.
 */
@ApplicationScope
class ImageRequestTransformer @Inject constructor() : Picasso.RequestTransformer {
    private val DEFAULT_ENDPOINT = "http://tnfs.tngou.net/image"

    override fun transformRequest(request: Request): Request {
        val builder = request.buildUpon()
        var uri = request.uri.toString()
        if (request.uri.scheme.isNullOrBlank()) {
            uri = DEFAULT_ENDPOINT + uri
            builder.setUri(Uri.parse(uri))
            return builder.build()
        }
        return request
    }
}
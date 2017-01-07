package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.BindingAdapter
import android.databinding.ObservableInt
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.github.grubber.tiangoumeitu.ui.widget.getPlaceholder
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
object BeautyDetailViewModel {
    private var _picasso by Delegates.notNull<Picasso>()
    var imageUrl by Delegates.notNull<String>()

    var progressO = ObservableInt()

    private val VISIBLE = View.VISIBLE
    private val GONE = View.GONE

    fun bind(picasso: Picasso, url: String) {
        _picasso = picasso
        imageUrl = url
    }

    @JvmStatic
    @BindingAdapter(*arrayOf("detailImageUrl"))
    fun loadImage(view: ImageView, url: String) {
        _picasso.load(url).placeholder(getPlaceholder(view.context))
                .into(object : com.squareup.picasso.Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        view.background = placeHolderDrawable
                        progressO.set(VISIBLE)
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        view.background = BitmapDrawable(view.resources, bitmap)
                        progressO.set(GONE)
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                        progressO.set(GONE)
                    }
                })
    }
}
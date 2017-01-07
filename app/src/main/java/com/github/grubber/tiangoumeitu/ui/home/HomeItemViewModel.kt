package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.github.grubber.tiangoumeitu.data.api.model.Gallery
import com.github.grubber.tiangoumeitu.ui.widget.getPlaceholder
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
object HomeItemViewModel {
    private var _picasso by Delegates.notNull<Picasso>()
    private var _gallery by Delegates.notNull<Gallery>()

    var imageUrl by Delegates.notNull<String>()
    var size by Delegates.notNull<String>()
    var title by Delegates.notNull<String>()

    fun bind(picasso: Picasso, gallery: Gallery) {
        _picasso = picasso
        _gallery = gallery

        imageUrl = _gallery.img
        size = _gallery.size.toString()
        title = _gallery.title
    }

    @JvmStatic
    @BindingAdapter(*arrayOf("imageUrl"))
    fun loadImage(view: ImageView, url: String) {
        _picasso.load(url).placeholder(getPlaceholder(view.context)).into(view)
    }

    fun onItemClick(view: View) {
        BeautyActivity.start(view.context, _gallery.id, _gallery.title, _gallery.size)
    }
}
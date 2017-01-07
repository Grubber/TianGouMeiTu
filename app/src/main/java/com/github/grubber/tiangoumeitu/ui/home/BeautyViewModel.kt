package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.ObservableField
import com.github.grubber.tiangoumeitu.data.api.model.PictureList
import com.github.grubber.tiangoumeitu.data.api.service.GalleryService
import rx.Observable

/**
 * Created by grubber on 2017/1/7.
 */
class BeautyViewModel {
    val titleO = ObservableField("")

    fun setTitle(index: Int, size: Int, title: String) {
        titleO.set("($index/$size) $title")
    }

    fun getPictureList(galleryService: GalleryService, id: Int): Observable<PictureList> {
        return galleryService.getPictureList(id)
    }
}
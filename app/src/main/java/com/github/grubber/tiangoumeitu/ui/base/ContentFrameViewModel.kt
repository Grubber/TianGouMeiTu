package com.github.grubber.tiangoumeitu.ui.base

import android.databinding.ObservableInt
import android.view.View
import com.github.grubber.tiangoumeitu.data.api.model.CommonResponse
import com.github.grubber.tiangoumeitu.util.Binder
import com.github.grubber.tiangoumeitu.util.applySchedulers
import com.github.grubber.tiangoumeitu.util.bind
import com.github.grubber.tiangoumeitu.util.plusAssign
import rx.Observable
import rx.lang.kotlin.PublishSubject
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class ContentFrameViewModel<T> {
    private var _binder by Delegates.notNull<Binder>()

    val contentO = ObservableInt()
    val emptyO = ObservableInt()
    val errorO = ObservableInt()

    private val VISIBLE = View.VISIBLE
    private val GONE = View.GONE

    private val _dataLoadedSubject = PublishSubject<List<T>>()
    val dataLoadedO = _dataLoadedSubject.asObservable()

    fun bind() {
        _binder = Binder.create()

        _refreshing()
    }

    fun loadData(page: Int = 0, block: (Int) -> Observable<CommonResponse<T>>) {
        _binder += block(page).applySchedulers().bind {
            next {
                _dataLoadedSubject.onNext(it.data)
                if (it.status) {
                    if (it.data.isNotEmpty()) {
                        _onDataLoaded()
                    } else {
                        _onEmpty()
                    }
                } else {
                    _onError()
                }
            }

            error {
                _dataLoadedSubject.onNext(listOf())
                _onError()
            }
        }
    }

    fun refresh(block: (Int) -> Observable<CommonResponse<T>>) {
        loadData { block(0) }
    }

    fun onRetry(block: (Int) -> Observable<CommonResponse<T>>) {
        _refreshing()
        loadData { block(0) }
    }

    private fun _refreshing() {
        contentO.set(GONE)
        emptyO.set(GONE)
        errorO.set(GONE)
    }

    private fun _onDataLoaded() {
        contentO.set(VISIBLE)
        emptyO.set(GONE)
        errorO.set(GONE)
    }

    private fun _onEmpty() {
        contentO.set(GONE)
        emptyO.set(VISIBLE)
        errorO.set(GONE)
    }

    private fun _onError() {
        contentO.set(GONE)
        emptyO.set(GONE)
        errorO.set(VISIBLE)
    }

    fun unbind() {
        _binder.clear()
    }
}
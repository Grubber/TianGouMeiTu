package com.github.grubber.tiangoumeitu.core

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus

/**
 * Created by grubber on 2017/1/7.
 */
object EventBus : Bus() {
    private val _mainThread: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun post(event: Any?) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event)
        } else {
            _mainThread.post { super.post(event) }
        }
    }
}
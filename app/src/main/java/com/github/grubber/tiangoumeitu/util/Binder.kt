package com.github.grubber.tiangoumeitu.util

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by grubber on 2017/1/7.
 */
class Binder private constructor() {
    companion object {
        fun create(): Binder {
            return Binder()
        }
    }

    private val _subscriptions by lazy { CompositeSubscription() }

    fun add(subscription: Subscription) {
        _subscriptions.add(subscription)
    }

    fun clear() {
        _subscriptions.clear()
    }
}
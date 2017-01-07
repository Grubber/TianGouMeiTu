package com.github.grubber.tiangoumeitu.ui.base

import rx.Observable
import rx.Subscription

/**
 * Created by grubber on 2017/1/7.
 */
interface LifecycleSubscriber {
    fun <T> bindObservable(observable: Observable<T>): Observable<T>

    fun <T> bindSubscribe(observable: Observable<T>, onNext: (T) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit,
                          onComplete: () -> Unit): Subscription
}
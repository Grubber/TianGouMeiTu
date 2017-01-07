package com.github.grubber.tiangoumeitu.ui.base

import com.trello.rxlifecycle.FragmentEvent
import rx.Observable
import rx.Subscription

/**
 * Created by grubber on 2017/1/7.
 */
interface FragmentLifecycleSubscriber : LifecycleSubscriber {
    fun <T> bindObservable(observable: Observable<T>, fragmentEvent: FragmentEvent): Observable<T>

    fun <T> bindSubscribe(observable: Observable<T>,
                          fragmentEvent: FragmentEvent,
                          onNext: (T) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          fragmentEvent: FragmentEvent,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          fragmentEvent: FragmentEvent,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit,
                          onComplete: () -> Unit): Subscription
}
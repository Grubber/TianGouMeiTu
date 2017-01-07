package com.github.grubber.tiangoumeitu.ui.base

import com.trello.rxlifecycle.ActivityEvent
import rx.Observable
import rx.Subscription

/**
 * Created by grubber on 2017/1/7.
 */
interface ActivityLifecycleSubscriber : LifecycleSubscriber {
    fun <T> bindObservable(observable: Observable<T>, activityEvent: ActivityEvent): Observable<T>

    fun <T> bindSubscribe(observable: Observable<T>,
                          activityEvent: ActivityEvent,
                          onNext: (T) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          activityEvent: ActivityEvent,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit): Subscription

    fun <T> bindSubscribe(observable: Observable<T>,
                          activityEvent: ActivityEvent,
                          onNext: (T) -> Unit,
                          onError: (Throwable) -> Unit,
                          onComplete: () -> Unit): Subscription
}
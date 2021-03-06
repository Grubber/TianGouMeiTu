package com.github.grubber.tiangoumeitu.ui.base

import android.os.Bundle
import com.github.grubber.tiangoumeitu.KLMTApplication
import com.github.grubber.tiangoumeitu.core.di.ApplicationComponent
import com.github.grubber.tiangoumeitu.core.di.HasComponent
import com.github.grubber.tiangoumeitu.core.di.Injectable
import com.github.grubber.tiangoumeitu.util.ToastHelper
import com.trello.rxlifecycle.FragmentEvent
import com.trello.rxlifecycle.components.support.RxFragment
import rx.Observable
import rx.Subscription
import javax.inject.Inject

/**
 * Created by grubber on 2017/1/7.
 */
abstract class BaseFragment : RxFragment(),
        HasComponent<ApplicationComponent>, Injectable, FragmentLifecycleSubscriber {
    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getComponent().inject(this)
        injectMembers()
    }

    override fun getComponent(): ApplicationComponent {
        return KLMTApplication.from(context).getComponent()
    }

    protected fun toast(resId: Int) {
        toast(getString(resId))
    }

    protected fun longToast(resId: Int) {
        longToast(getString(resId))
    }

    protected fun toast(message: String) {
        toastHelper.show(message)
    }

    protected fun longToast(message: String) {
        toastHelper.showLongToast(message)
    }

    override fun <T> bindObservable(observable: Observable<T>): Observable<T> {
        return observable.compose(this.bindToLifecycle<T>())
    }

    override fun <T> bindSubscribe(observable: Observable<T>, onNext: (T) -> Unit): Subscription {
        return bindObservable(observable).subscribe(onNext)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit): Subscription {
        return bindObservable(observable).subscribe(onNext, onError)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit,
                                   onComplete: () -> Unit): Subscription {
        return bindObservable(observable).subscribe(onNext, onError, onComplete)
    }

    override fun <T> bindObservable(observable: Observable<T>, fragmentEvent: FragmentEvent): Observable<T> {
        return observable.compose(this.bindUntilEvent<T>(fragmentEvent))
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   fragmentEvent: FragmentEvent,
                                   onNext: (T) -> Unit): Subscription {
        return bindObservable(observable, fragmentEvent).subscribe(onNext)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   fragmentEvent: FragmentEvent,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit): Subscription {
        return bindObservable(observable, fragmentEvent).subscribe(onNext, onError)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   fragmentEvent: FragmentEvent,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit,
                                   onComplete: () -> Unit): Subscription {
        return bindObservable(observable, fragmentEvent).subscribe(onNext, onError, onComplete)
    }
}
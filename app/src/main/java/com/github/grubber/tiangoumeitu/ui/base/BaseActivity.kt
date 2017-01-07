package com.github.grubber.tiangoumeitu.ui.base

import android.os.Bundle
import android.view.MenuItem
import com.github.grubber.tiangoumeitu.KLMTApplication
import com.github.grubber.tiangoumeitu.core.di.ApplicationComponent
import com.github.grubber.tiangoumeitu.core.di.HasComponent
import com.github.grubber.tiangoumeitu.core.di.Injectable
import com.github.grubber.tiangoumeitu.util.ToastHelper
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import rx.Observable
import rx.Subscription
import javax.inject.Inject

/**
 * Created by grubber on 2017/1/7.
 */
abstract class BaseActivity : RxAppCompatActivity(),
        HasComponent<ApplicationComponent>, Injectable, ActivityLifecycleSubscriber {
    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
        injectMembers()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun getComponent(): ApplicationComponent {
        return KLMTApplication.from(this).getComponent()
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

    override fun <T> bindObservable(observable: Observable<T>, activityEvent: ActivityEvent): Observable<T> {
        return observable.compose(this.bindUntilEvent<T>(activityEvent))
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   activityEvent: ActivityEvent,
                                   onNext: (T) -> Unit): Subscription {
        return bindObservable(observable, activityEvent).subscribe(onNext)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   activityEvent: ActivityEvent,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit): Subscription {
        return bindObservable(observable, activityEvent).subscribe(onNext, onError)
    }

    override fun <T> bindSubscribe(observable: Observable<T>,
                                   activityEvent: ActivityEvent,
                                   onNext: (T) -> Unit,
                                   onError: (Throwable) -> Unit,
                                   onComplete: () -> Unit): Subscription {
        return bindObservable(observable, activityEvent).subscribe(onNext, onError, onComplete)
    }
}
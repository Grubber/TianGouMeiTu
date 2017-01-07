package com.github.grubber.tiangoumeitu

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.github.grubber.tiangoumeitu.core.di.*
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class KLMTApplication : MultiDexApplication(), HasComponent<ApplicationComponent> {
    private var _component by Delegates.notNull<ApplicationComponent>()

    override fun onCreate() {
        super.onCreate()

        _setupObjectGraph()
        _setupAnalytics()
    }

    private fun _setupObjectGraph() {
        _component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule())
                .apiModule(ApiModule())
                .utilsModule(UtilsModule())
                .build()
    }

    private fun _setupAnalytics() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun getComponent(): ApplicationComponent {
        return _component
    }

    companion object {
        fun from(context: Context) = context.applicationContext as KLMTApplication
    }
}
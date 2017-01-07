package com.github.grubber.tiangoumeitu.core.di

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangoumeitu.core.di.qualifier.ForApplication
import com.github.grubber.tiangoumeitu.util.checkNotNull
import dagger.Module
import dagger.Provides

/**
 * Created by grubber on 2017/1/7.
 */
@Module
class AndroidModule {
    private val _applicationContext: Context

    constructor(application: Application) {
        _applicationContext = checkNotNull(application, "Context can not be null.")
    }

    @ApplicationScope
    @Provides
    @ForApplication
    fun provideApplicationContext(): Context {
        return _applicationContext
    }

    @ApplicationScope
    @Provides
    fun provideAssetManager(@ForApplication context: Context): AssetManager {
        return context.assets
    }
}
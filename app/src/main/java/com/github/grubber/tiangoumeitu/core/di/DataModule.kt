package com.github.grubber.tiangoumeitu.core.di

import android.content.Context
import android.content.SharedPreferences
import com.github.grubber.tiangoumeitu.core.EventBus
import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangoumeitu.core.di.qualifier.ForApplication
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides

/**
 * Created by grubber on 2017/1/7.
 */
@Module
class DataModule {
    @Provides
    @ApplicationScope
    fun provideSharedPreferences(@ForApplication context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @ApplicationScope
    fun provideEventBus(): Bus {
        return EventBus
    }
}
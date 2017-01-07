package com.github.grubber.tiangoumeitu.core.di

import android.content.Context
import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangoumeitu.core.di.qualifier.ForApplication
import com.github.grubber.tiangoumeitu.util.ToastHelper
import dagger.Module
import dagger.Provides

/**
 * Created by grubber on 2017/1/7.
 */
@Module
class UtilsModule {
    @Provides
    @ApplicationScope
    fun provideToastHelper(@ForApplication context: Context): ToastHelper {
        return ToastHelper(context)
    }
}
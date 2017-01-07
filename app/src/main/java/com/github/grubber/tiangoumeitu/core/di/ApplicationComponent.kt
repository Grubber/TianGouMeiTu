package com.github.grubber.tiangoumeitu.core.di

import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import dagger.Component

/**
 * Created by grubber on 2017/1/7.
 */
@ApplicationScope
@Component(modules = arrayOf(
        AndroidModule::class,
        DataModule::class,
        NetworkModule::class,
        ApiModule::class,
        UtilsModule::class
))
interface ApplicationComponent : ViewInjector
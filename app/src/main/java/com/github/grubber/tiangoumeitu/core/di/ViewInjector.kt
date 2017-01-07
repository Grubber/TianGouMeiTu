package com.github.grubber.tiangoumeitu.core.di

import com.github.grubber.tiangoumeitu.ui.base.BaseActivity
import com.github.grubber.tiangoumeitu.ui.base.BaseFragment
import com.github.grubber.tiangoumeitu.ui.home.BeautyActivity
import com.github.grubber.tiangoumeitu.ui.home.BeautyFragment
import com.github.grubber.tiangoumeitu.ui.home.HomeFragment

/**
 * Created by grubber on 2017/1/7.
 */
interface ViewInjector {
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(beautyActivity: BeautyActivity)
    fun inject(beautyFragment: BeautyFragment)
}
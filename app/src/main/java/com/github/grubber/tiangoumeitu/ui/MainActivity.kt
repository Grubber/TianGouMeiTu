package com.github.grubber.tiangoumeitu.ui

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.WindowManager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.databinding.ActivityMainBinding
import com.github.grubber.tiangoumeitu.ui.base.BaseActivity
import com.github.grubber.tiangoumeitu.ui.favorite.FavoriteFragment
import com.github.grubber.tiangoumeitu.ui.home.HomeFragment
import com.github.grubber.tiangoumeitu.ui.search.SearchFragment
import com.github.grubber.tiangoumeitu.ui.settings.SettingsFragment
import com.github.grubber.tiangoumeitu.util.newInstance
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class MainActivity : BaseActivity() {
    private var _binding by Delegates.notNull<ActivityMainBinding>()

    private val _fragments by lazy {
        listOf(
                newInstance<HomeFragment>(),
                newInstance<SearchFragment>(),
                newInstance<FavoriteFragment>(),
                newInstance<SettingsFragment>()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(_binding.bottomNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)
            setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)

            activeColor = android.R.color.white
            inActiveColor = android.R.color.darker_gray
            setBarBackgroundColor(android.R.color.black)

            addItem(BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home"))
            addItem(BottomNavigationItem(R.drawable.ic_search_white_24dp, "Search"))
            addItem(BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "Favorite"))
            addItem(BottomNavigationItem(R.drawable.ic_person_white_24dp, "Settings"))

            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabUnselected(position: Int) {
                }

                override fun onTabReselected(position: Int) {
                }

                override fun onTabSelected(position: Int) {
                    Timber.d("onTabSelected: position is $position")
                    _showContentFragment(supportFragmentManager.beginTransaction(), position)
                }
            })

            initialise()
        }

        _initAndShowDefaultFragment()
    }

    private fun _initAndShowDefaultFragment() {
        with(supportFragmentManager.beginTransaction()) {
            _fragments.forEach {
                add(R.id.content_frame, it)
            }
            _showContentFragment(this, 0)
        }
    }

    private fun _showContentFragment(fragmentTransaction: FragmentTransaction, index: Int) {
        with(fragmentTransaction) {
            _fragments.forEachIndexed { i, fragment ->
                if (i == index) show(fragment) else hide(fragment)
            }
            commit()
        }
    }

    override fun injectMembers() {
    }
}
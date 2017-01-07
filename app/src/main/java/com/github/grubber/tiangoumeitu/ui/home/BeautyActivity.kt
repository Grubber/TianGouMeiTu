package com.github.grubber.tiangoumeitu.ui.home

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.WindowManager
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.data.api.service.GalleryService
import com.github.grubber.tiangoumeitu.databinding.ActivityBeautyBinding
import com.github.grubber.tiangoumeitu.ui.base.BaseActivity
import com.github.grubber.tiangoumeitu.util.applySchedulers
import com.jakewharton.rxbinding.support.v4.view.pageSelections
import rx.Observable
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class BeautyActivity : BaseActivity() {
    companion object {
        const val EXTRA_GALLERY_ID = "gallery_id"
        const val EXTRA_GALLERY_TITLE = "gallery_title"
        const val EXTRA_GALLERY_SIZE = "gallery_size"

        fun start(context: Context, id: Int, title: String, size: Int) {
            val intent = Intent(context, BeautyActivity::class.java)
            intent.putExtra(EXTRA_GALLERY_ID, id)
            intent.putExtra(EXTRA_GALLERY_TITLE, title)
            intent.putExtra(EXTRA_GALLERY_SIZE, size)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var galleryService: GalleryService

    private var _binding by Delegates.notNull<ActivityBeautyBinding>()
    private val _viewModel by lazy { BeautyViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_beauty)
        _binding.viewModel = _viewModel

        setSupportActionBar(_binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_GALLERY_ID, 0)
        val title = intent.getStringExtra(EXTRA_GALLERY_TITLE)
        val size = intent.getIntExtra(EXTRA_GALLERY_SIZE, 0)

        _viewModel.setTitle(1, size, title)

        bindSubscribe(_binding.viewPager.pageSelections()) {
            _viewModel.setTitle(it + 1, size, title)
        }

        bindSubscribe(_viewModel.getPictureList(galleryService, id)
                .flatMap {
                    Observable.just(it.list.map { it.src }.toList())
                }
                .applySchedulers()) {
            val adapter = BeautyAdapter(it, supportFragmentManager)
            _binding.viewPager.adapter = adapter
        }
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }
}

class BeautyAdapter(var srcs: List<String>,
                    var fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return srcs.size
    }

    override fun getItem(position: Int): Fragment? {
        return BeautyFragment.newInstance(srcs[position])
    }
}
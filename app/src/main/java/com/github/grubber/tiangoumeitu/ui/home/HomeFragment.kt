package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.data.api.model.Gallery
import com.github.grubber.tiangoumeitu.data.api.service.GalleryService
import com.github.grubber.tiangoumeitu.databinding.FragmentContentFrameBinding
import com.github.grubber.tiangoumeitu.ui.base.BaseFragment
import com.github.grubber.tiangoumeitu.ui.base.ContentFrameViewModel
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import com.jakewharton.rxbinding.view.clicks
import com.squareup.picasso.Picasso
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var galleryService: GalleryService
    @Inject
    lateinit var picasso: Picasso

    private var _binding by Delegates.notNull<FragmentContentFrameBinding>()
    private val _homeViewModel by lazy { ContentFrameViewModel<Gallery>() }
    private var _homeAdapter by Delegates.notNull<HomeAdapter>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content_frame, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _homeViewModel.bind()
        _homeViewModel.loadData {
            galleryService.getGalleries()
        }
        _binding.viewModel = _homeViewModel

        with(_binding.refreshLayout) {
            post {
                isRefreshing = true
            }

            bindSubscribe(refreshes()) {
                _homeViewModel.refresh {
                    galleryService.getGalleries()
                }
            }
        }

        _homeAdapter = HomeAdapter(picasso)
        with(_binding.recyclerView) {
            adapter = _homeAdapter
            layoutManager = LinearLayoutManager(context)
        }

        bindSubscribe(_binding.viewModel.dataLoadedO) {
            _binding.refreshLayout.isRefreshing = false
            _homeAdapter.items = it as List<Gallery>
        }
        bindSubscribe(_binding.networkErrorView.root.clicks()) {
            _homeViewModel.onRetry {
                galleryService.getGalleries()
            }
        }
    }

    override fun onDestroy() {
        _homeViewModel.unbind()
        super.onDestroy()
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }
}
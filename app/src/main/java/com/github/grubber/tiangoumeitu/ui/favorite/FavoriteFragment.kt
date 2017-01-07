package com.github.grubber.tiangoumeitu.ui.favorite

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.databinding.FragmentFavoriteBinding
import com.github.grubber.tiangoumeitu.ui.base.BaseFragment
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class FavoriteFragment : BaseFragment() {
    private var _binding by Delegates.notNull<FragmentFavoriteBinding>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return _binding.root
    }

    override fun injectMembers() {
    }
}
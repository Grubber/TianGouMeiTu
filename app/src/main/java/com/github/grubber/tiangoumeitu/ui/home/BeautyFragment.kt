package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.databinding.FragmentBeautyBinding
import com.github.grubber.tiangoumeitu.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/7.
 */
class BeautyFragment : BaseFragment() {
    companion object {
        const val EXTRA_IMG_SRC = "img_src"

        fun newInstance(src: String): BeautyFragment {
            val args = Bundle()
            args.putString(EXTRA_IMG_SRC, src)
            val fragment = BeautyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var picasso: Picasso

    private var _binding by Delegates.notNull<FragmentBeautyBinding>()
    private var _viewModel by Delegates.notNull<BeautyDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_beauty,
                container,
                false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _viewModel = BeautyDetailViewModel
        _viewModel.bind(picasso, arguments.getString(EXTRA_IMG_SRC))
        _binding.viewModel = _viewModel
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }
}
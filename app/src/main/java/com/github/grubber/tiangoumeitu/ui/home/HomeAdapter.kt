package com.github.grubber.tiangoumeitu.ui.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grubber.tiangoumeitu.R
import com.github.grubber.tiangoumeitu.data.api.model.Gallery
import com.github.grubber.tiangoumeitu.databinding.ItemHomeAdapterBinding
import com.squareup.picasso.Picasso

/**
 * Created by grubber on 2017/1/7.
 */
class HomeAdapter(private val _picasso: Picasso) : RecyclerView.Adapter<HomeViewHolder>() {
    var items = listOf<Gallery>()
        set(value) {
            field += value
            field = field.distinct()
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder? {
        val binding: ItemHomeAdapterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_home_adapter,
                parent,
                false)
        return HomeViewHolder(binding, _picasso)
    }
}

class HomeViewHolder(
        private val _binding: ItemHomeAdapterBinding,
        private val _picasso: Picasso,
        itemView: View? = _binding.itemHome) : RecyclerView.ViewHolder(itemView) {
    fun bind(gallery: Gallery) {
        val viewModel = HomeItemViewModel
        viewModel.bind(_picasso, gallery)
        _binding.viewModel = viewModel
    }
}
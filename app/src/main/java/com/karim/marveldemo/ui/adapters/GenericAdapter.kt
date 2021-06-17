package com.karim.marveldemo.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karim.marveldemo.R
import com.karim.marveldemo.data.GenericResource
import com.karim.marveldemo.databinding.ItemGenericBinding
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class GenericAdapter :
    BindingListAdapter<GenericResource, GenericAdapter.GenericItemViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemViewHolder {
        val binding = parent.binding<ItemGenericBinding>(R.layout.item_generic)
        return GenericItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position =
                    bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
            }
        }
    }

    override fun onBindViewHolder(holder: GenericItemViewHolder, position: Int) {
        holder.binding.apply {
            itemGeneric = getItem(position)
            executePendingBindings()
        }
    }


    class GenericItemViewHolder(val binding: ItemGenericBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GenericResource>() {

            override fun areItemsTheSame(
                oldItem: GenericResource,
                newItem: GenericResource
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: GenericResource,
                newItem: GenericResource
            ): Boolean =
                oldItem == newItem
        }
    }
}
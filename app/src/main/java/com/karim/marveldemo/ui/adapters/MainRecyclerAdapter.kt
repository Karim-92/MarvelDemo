package com.karim.marveldemo.ui.adapters

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.karim.marveldemo.R
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.databinding.ItemCharacterBinding
import com.karim.marveldemo.ui.details.DetailsActivity
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class MainRecyclerAdapter() :
    BindingListAdapter<MarvelCharacter, MainRecyclerAdapter.CharacterViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = parent.binding<ItemCharacterBinding>(R.layout.item_character)
        return CharacterViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position =
                    bindingAdapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    DetailsActivity.startActivity(
                        binding.transformationLayout,
                        getItem(position)
                    )
                    onClickedAt = currentClickedAt
                }
            }
        }
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            characterData = getItem(position)
            executePendingBindings()
        }
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MarvelCharacter>() {

            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean =
                oldItem == newItem
        }
    }
}





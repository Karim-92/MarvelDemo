package com.karim.marveldemo.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.karim.marveldemo.R
import com.karim.marveldemo.data.CharacterData
import com.karim.marveldemo.databinding.ItemCharacterBinding
import com.skydoves.bindables.binding

class MainRecyclerAdapter() :
    RecyclerView.Adapter<MainRecyclerAdapter.CharacterViewHolder>() {

    private val items = mutableListOf<CharacterData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = parent.binding<ItemCharacterBinding>(R.layout.item_character)

        return CharacterViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position =
                    bindingAdapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
                val mainFrag = MainFragment()
                mainFrag.startDetailsFragment(binding.transformationLayout, items[position])
            }
        }
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            characterData = items[position]
            executePendingBindings()
        }
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateCharacterList(posters: List<CharacterData>) {
        items.clear()
        items.addAll(posters)
        notifyDataSetChanged()
    }
}





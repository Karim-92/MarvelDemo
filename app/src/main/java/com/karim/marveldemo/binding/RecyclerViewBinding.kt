package com.karim.marveldemo.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karim.marveldemo.data.CharacterData
import com.karim.marveldemo.ui.main.MainRecyclerAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
        view.adapter = baseAdapter
    }

    @JvmStatic
    @BindingAdapter("adapterCharacterList")
    fun bindAdapterCharacterList(view: RecyclerView, posters: List<CharacterData>?) {
        posters.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<MainRecyclerAdapter> { adapter ->
                adapter.updateCharacterList(items)
            }
        }
    }
}
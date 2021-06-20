package com.karim.marveldemo.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karim.marveldemo.ui.main.MainViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("characterList")
    fun bindCharacterList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("resourceList")
    fun bindResourceList(view: DiscreteScrollView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
        view.apply {
            setSlideOnFling(true)
            setSlideOnFlingThreshold(500)
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.1f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.LEFT)
                    .build()
            )
        }
    }

    @JvmStatic
    @BindingAdapter("paginationCharacterList")
    fun paginationCharacterList(view: RecyclerView, viewModel: MainViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isLoading },
            loadMore = { viewModel.getNextCharacterList() },
            onLast = { false }
        ).run {
            threshold = 10
        }
    }
}
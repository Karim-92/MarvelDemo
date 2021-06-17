package com.karim.marveldemo.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.karim.marveldemo.R

object ViewBinding {

    @JvmStatic
    @BindingAdapter("resourceImage")
    fun bindLoadImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.image_unavailable)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

}
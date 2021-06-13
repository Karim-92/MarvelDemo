package com.karim.marveldemo.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewBinding {

    @JvmStatic
    @BindingAdapter("characterImage")
    fun bindLoadImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

}
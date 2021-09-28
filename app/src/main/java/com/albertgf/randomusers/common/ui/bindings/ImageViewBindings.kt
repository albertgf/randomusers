package com.albertgf.randomusers.common.ui.bindings

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.albertgf.randomusers.common.extensions.downloadIntoCircle

@BindingAdapter("loadImageCircle")
fun loadImageCircle(imageView: AppCompatImageView, url: String?) {
    url?.let{
        imageView.downloadIntoCircle(url)
    }
}
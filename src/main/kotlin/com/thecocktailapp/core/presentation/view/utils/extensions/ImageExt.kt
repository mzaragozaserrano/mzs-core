package com.thecocktailapp.core.presentation.view.utils.extensions

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thecocktailapp.core.presentation.view.utils.GlideApp

fun AppCompatImageView.loadImageFromUrl(
    @DrawableRes placeHolderId: Int,
    url: String,
) {
    GlideApp.with(context)
        .load(Uri.parse(url))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().placeholder(placeHolderId).centerCrop())
        .into(this)
}
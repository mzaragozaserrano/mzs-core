package com.mzs.core.presentation.utils.extensions

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mzs.core.presentation.utils.generic.GlideApp

fun AppCompatImageView.loadImageFromUrl(@DrawableRes placeHolderId: Int, url: String) {
    GlideApp.with(context)
        .load(Uri.parse(url))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().placeholder(placeHolderId).centerCrop())
        .into(this)
}
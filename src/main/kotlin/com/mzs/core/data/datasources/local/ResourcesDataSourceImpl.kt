package com.mzs.core.data.datasources.local

import android.content.Context
import androidx.annotation.StringRes

class ResourcesDataSourceImpl(private val context: Context) : ResourcesDataSource {

    override fun getStringFromResource(@StringRes resId: Int): String = context.getString(resId)

    override fun getStringOrResource(@StringRes resId: Int, str: String?): String =
        if (str.isNullOrEmpty()) {
            context.getString(resId)
        } else {
            str
        }

}
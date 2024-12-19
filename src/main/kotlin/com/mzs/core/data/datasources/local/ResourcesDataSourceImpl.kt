package com.mzs.core.data.datasources.local

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourcesDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ResourcesDataSource {

    override fun getStringFromResource(@StringRes resId: Int): String = context.getString(resId)

    override fun getStringOrResource(str: String?, @StringRes resId: Int): String =
        if (str.isNullOrEmpty()) {
            context.getString(resId)
        } else {
            str
        }

}
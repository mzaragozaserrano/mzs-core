package com.mzs.core.data.datasources.local

import androidx.annotation.StringRes

interface ResourcesDataSource {
    fun getStringFromResource(@StringRes resId: Int): String
    fun getStringOrResource(@StringRes resId: Int, str: String?): String
}
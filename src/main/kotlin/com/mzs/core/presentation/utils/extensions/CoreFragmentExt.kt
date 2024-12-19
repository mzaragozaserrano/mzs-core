package com.mzs.core.presentation.utils.extensions

import androidx.fragment.app.Fragment
import com.mzs.core.presentation.base.CoreBaseActivity
import java.io.Serializable

fun Fragment.hideProgressDialog() {
    (activity as CoreBaseActivity<*, *, *, *, *, *>).hideProgressDialog()
}

fun Fragment.showProgressDialog() {
    (activity as CoreBaseActivity<*, *, *, *, *, *>).showProgressDialog()
}

fun <T : Serializable> Fragment.getSerializableExtra(key: String, clazz: Class<T>): T? =
    (activity as CoreBaseActivity<*, *, *, *, *, *>).getSerializableExtra(key, clazz)
package com.mzs.core.presentation.utils.extensions

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.mzs.core.presentation.base.CoreBaseActivity
import java.io.Serializable

fun Fragment.hideProgressDialog() {
    (activity as CoreBaseActivity<*, *, *, *, *, *>).hideProgressDialog()
}

fun Fragment.showProgressDialog() {
    (activity as CoreBaseActivity<*, *, *, *, *, *>).showProgressDialog()
}

fun Fragment.setUpMenuToolbar(
    allowGoBack: Boolean = false,
    drawerLayout: DrawerLayout,
    @ColorRes scrimColorId: Int,
    @ColorRes titleColorId: Int,
    @StringRes titleTextId: Int? = null,
    toolbar: Toolbar,
    @ColorRes toolbarBackgroundColorId: Int,
    toolbarTitle: AppCompatTextView? = null,
) {
    (activity as CoreBaseActivity<*, *, *, *, *, *>).setUpMenuToolbar(
        activity = (activity as CoreBaseActivity<*, *, *, *, *, *>),
        allowGoBack = allowGoBack,
        drawerLayout = drawerLayout,
        scrimColorId = scrimColorId,
        titleColorId = titleColorId,
        titleTextId = titleTextId,
        toolbar = toolbar,
        toolbarBackgroundColorId = toolbarBackgroundColorId,
        toolbarTitle = toolbarTitle
    )
}

fun <T : Serializable> Fragment.getSerializableExtra(key: String, clazz: Class<T>): T? =
    (activity as CoreBaseActivity<*, *, *, *, *, *>).getSerializableExtra(key, clazz)
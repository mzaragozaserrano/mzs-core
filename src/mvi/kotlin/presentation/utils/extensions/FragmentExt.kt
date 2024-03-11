package presentation.utils.extensions

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import presentation.base.BaseActivity

fun Fragment.hideProgressDialog() {
    (activity as BaseActivity<*, *, *, *>).hideProgressDialog()
}

fun Fragment.showProgressDialog() {
    (activity as BaseActivity<*, *, *, *>).showProgressDialog()
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
    (activity as BaseActivity<*, *, *, *>).setUpMenuToolbar(
        activity = (activity as BaseActivity<*, *, *, *>),
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
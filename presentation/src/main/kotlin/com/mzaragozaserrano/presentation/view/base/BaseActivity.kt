package com.mzaragozaserrano.presentation.view.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.view.utils.emptyText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>> :
    AppCompatActivity() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    open fun renderView(state: S) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.initSetup()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mutableViewState.collectLatest { state ->
                    renderView(state)
                }
            }
        }
        setContentView(binding.root)
    }

    private fun VB.initSetup() {
        setUpListeners()
        setUpView()
    }

    open fun VB.setUpListeners() {}
    open fun VB.setUpView() {}

    fun setUpMenuToolbar(
        activity: Activity,
        allowGoBack: Boolean,
        drawerLayout: DrawerLayout,
        @ColorRes scrimColorId: Int,
        @ColorRes titleColorId: Int,
        @StringRes titleTextId: Int?,
        toolbar: Toolbar,
        @ColorRes toolbarBackgroundColorId: Int,
        toolbarTitle: AppCompatTextView?,
    ) {

        val iconNavigationId = if(allowGoBack) R.drawable.core_ic_arrow_back else R.drawable.core_ic_menu

        val scrimColor = ContextCompat.getColor(applicationContext, scrimColorId)
        val titleColor = ContextCompat.getColor(applicationContext, titleColorId)
        val toolbarBackgroundColor =
            ContextCompat.getColor(applicationContext, toolbarBackgroundColorId)

        val toggle = ActionBarDrawerToggle(
            activity,
            drawerLayout,
            toolbar,
            R.string.core_open_navigation_drawer,
            R.string.core_close_navigation_drawer
        )

        titleTextId?.let { titleId ->
            toolbarTitle?.apply {
                text = getString(titleId)
                setTextColor(titleColor)
                setBackgroundColor(toolbarBackgroundColor)
            }
        }

        with(toolbar) {
            title = emptyText
            setBackgroundColor(toolbarBackgroundColor)
            setNavigationIcon(iconNavigationId)
            when (iconNavigationId) {
                R.drawable.core_ic_menu -> {
                    drawerLayout.apply {
                        setScrimColor(scrimColor)
                        drawerElevation = 50f
                    }
                    toggle.syncState()
                }

                R.drawable.core_ic_arrow_back -> {
                    setNavigationOnClickListener {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        }
    }

    fun emitAction(intent: I) {
        lifecycleScope.launch {
            viewModel.intentFlow.emit(intent)
        }
    }

    fun toastLong(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            applicationContext?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
    }

    open fun <T> clearAndNavigateToNewActivity(className: Class<T>, bundle: Bundle? = null) {
        val intent = Intent(this, className)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        bundle?.let { extras -> intent.putExtras(extras) }
        startActivity(intent)
    }

}
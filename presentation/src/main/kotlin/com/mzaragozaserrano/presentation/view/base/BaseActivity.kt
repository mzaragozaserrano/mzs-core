package com.mzaragozaserrano.presentation.view.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.view.utils.emptyText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>> :
    AppCompatActivity() {

    open var loadingRaw: Int? = null

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    private val layoutInflater: View by lazy {
        View.inflate(applicationContext, R.layout.core_layout_loading, null)
    }

    private val animation: LottieAnimationView by lazy {
        layoutInflater.findViewById(R.id.animation_view_loader)
    }

    private val progressDialog: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.CoreDialogTransparent)
            .setView(layoutInflater)
            .setCancelable(false)
            .create()
    }

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

    open fun <T> clearAndNavigateToNewActivity(className: Class<T>, bundle: Bundle? = null) {
        val intent = Intent(this, className)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        bundle?.let { extras -> intent.putExtras(extras) }
        startActivity(intent)
    }

    open fun renderView(state: S) {}

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

        val iconNavigationId =
            if (allowGoBack) R.drawable.core_ic_arrow_back else R.drawable.core_ic_menu

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

    fun hideKeyboard() {
        val imm: InputMethodManager =
            applicationContext?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            animation.cancelAnimation()
            progressDialog.dismiss()
        }
    }

    fun showProgressDialog() {
        if (!progressDialog.isShowing) {
            animation.playAnimation()
            progressDialog.show()
        }
    }

    fun toastLong(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun VB.initSetup() {
        setUpAnimation()
        setUpListeners()
        setUpView()
    }

    private fun setUpAnimation() {
        loadingRaw?.let { animation.setAnimation(it) }
    }

}
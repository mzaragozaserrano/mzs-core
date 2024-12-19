package com.mzs.core.presentation.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import com.mzs.core.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class CoreBaseActivity<State, Intent, Action, Result, VB : ViewBinding, VM : CoreMVIViewModel<State, Intent, Action, Result>> :
    AppCompatActivity() {

    open var loadingRaw: Int? = null

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    protected abstract fun renderView(state: State)

    private val animationLayoutInflater: View by lazy {
        View.inflate(applicationContext, R.layout.core_layout_loading, null)
    }

    private val animation: LottieAnimationView by lazy {
        animationLayoutInflater.findViewById(R.id.animation_view_loader)
    }

    private val progressDialog: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.CoreDialogTransparent)
            .setView(animationLayoutInflater)
            .setCancelable(false)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAnimation()
        with(binding) {
            setUpView()
        }
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
        intent.flags =
            android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK
        bundle?.let { extras -> intent.putExtras(extras) }
        startActivity(intent)
    }

    open fun VB.setUpView() {}

    fun emitAction(intent: Intent) {
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
        if (progressDialog.isShowing && loadingRaw != null) {
            animation.cancelAnimation()
            progressDialog.dismiss()
        }
    }

    fun showProgressDialog() {
        if (!progressDialog.isShowing && loadingRaw != null) {
            animation.playAnimation()
            progressDialog.show()
        }
    }

    fun toastLong(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun setUpAnimation() {
        loadingRaw?.let { animation.setAnimation(it) }
    }

}
package com.mzaragozaserrano.presentation.view.base

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>> :
    AppCompatActivity() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    open fun renderView(state: S) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSetup()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mutableViewState.collectLatest { state ->
                    renderView(state)
                }
            }
        }
        setContentView(binding.root)
    }

    private fun initSetup() {
        setUpListeners()
        setUpView()
    }

    open fun setUpListeners() {}
    open fun setUpView() {}

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

}
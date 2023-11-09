package com.mzaragozaserrano.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>>(
    @LayoutRes open val layout: Int,
) : Fragment(), CoroutineScope by MainScope() {

    private var callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    protected abstract val binding: VB
    protected abstract val viewModel: VM
    protected abstract fun renderView(state: S)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mutableViewState.collectLatest { state ->
                    renderView(state)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initSetup()
        onBackPressedDispatcher(this)
    }

    private fun onBackPressedDispatcher(lifecycleOwner: LifecycleOwner) {
        requireActivity().onBackPressedDispatcher.addCallback(lifecycleOwner, callback)
    }

    private fun VB.initSetup() {
        setUpListeners()
    }

    open fun VB.setUpListeners() {}

    open fun onBackPressed() {}

    fun emitAction(intent: I) {
        lifecycleScope.launch {
            viewModel.intentFlow.emit(intent)
        }
    }

}
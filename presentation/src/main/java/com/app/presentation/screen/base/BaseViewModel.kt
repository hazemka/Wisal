package com.app.presentation.screen.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<T, E>(
    initialState: T
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<T> = _uiState.asStateFlow()

    private val _uiEffect = Channel<E>()
    val uiEffect = _uiEffect.receiveAsFlow()

    protected fun updateState(transform: (T) -> T) {
        _uiState.update { transform(it) }
    }

    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            _uiEffect.send(event)
        }
    }

    protected fun <R> launchWithResult(
        action: suspend () -> R,
        onSuccess: (R) -> Unit,
        onError: (Int) -> Unit,
        onStart: () -> Unit = {},
        onFinally: () -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            onStart()
            runCatching { action() }
                .onSuccess(onSuccess)
                .onFailure { e ->
                    val msg = (e as Exception).hashCode()
                    onError(msg)
                }
            onFinally()
        }
    }
}
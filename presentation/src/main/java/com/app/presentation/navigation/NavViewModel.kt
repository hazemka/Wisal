package com.app.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.repository.PreferencesRepository
import kotlinx.coroutines.launch

class NavViewModel(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination = _startDestination

    init {
        viewModelScope.launch {
            val isComplete = preferencesRepository.getLoginSuccess()
            _startDestination.value =
                if (isComplete) WisalScreens.HomeScreen.route else WisalScreens.LoginScreen.route
        }
    }
}
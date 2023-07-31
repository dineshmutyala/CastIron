package com.dinesh.castiron

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var state by mutableStateOf(MainScreenState(false))
        private set

    init {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.Default).launch {
                delay(2000)
                state = state.copy(isLoggedIn = true)
            }
        }
    }
}
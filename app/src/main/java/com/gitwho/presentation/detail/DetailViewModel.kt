package com.gitwho.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitwho.common.Resource
import com.gitwho.domain.use_case.GetUserByUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state

    /**
     * Get data related to bound location
     */
    fun getUserByUsername(login: String) {
        getUserByUsernameUseCase(
            login
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = UserState(item = it.data)
                }
                is Resource.Error -> {
                    _state.value = UserState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}
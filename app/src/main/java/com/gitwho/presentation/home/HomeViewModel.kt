package com.gitwho.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitwho.common.Resource
import com.gitwho.domain.use_case.GetUserBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserBySearchUseCase: GetUserBySearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUserState())
    val state: StateFlow<SearchUserState> = _state

    /**
     * Get data related to bound location
     */
    fun getUserByQuery(query: String) {
        getUserBySearchUseCase(
            query
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SearchUserState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchUserState(data = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SearchUserState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}
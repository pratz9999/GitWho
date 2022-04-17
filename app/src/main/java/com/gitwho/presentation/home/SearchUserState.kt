package com.gitwho.presentation.home

import com.gitwho.domain.model.User

data class SearchUserState(
    val isLoading: Boolean = false,
    val data: List<User> = emptyList(),
    val error: String = ""
)
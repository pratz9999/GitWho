package com.gitwho.presentation.detail

import com.gitwho.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val item: User? = null,
    val error: String = ""
)
package com.gitwho.domain.repository

import com.gitwho.data.remote.dto.SearchResponse
import com.gitwho.data.remote.dto.UserResponse

interface UserRepository {
    suspend fun getUserByName(query: String): SearchResponse
    suspend fun getUserByUsername(username: String): UserResponse
}
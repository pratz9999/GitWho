package com.gitwho.data.repository

import com.gitwho.data.remote.ApiService
import com.gitwho.data.remote.dto.SearchResponse
import com.gitwho.data.remote.dto.UserResponse
import com.gitwho.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override suspend fun getUserByName(query: String): SearchResponse {
        return api.getUserByName(ApiService.ACCEPT_TOKEN, query)
    }

    override suspend fun getUserByUsername(username: String): UserResponse {
        return api.getUserByUsername(ApiService.ACCEPT_TOKEN, username)
    }
}
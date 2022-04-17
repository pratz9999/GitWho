package com.gitwho.data.repository

import android.util.Log
import com.gitwho.common.DummyData
import com.gitwho.data.remote.dto.SearchResponse
import com.gitwho.data.remote.dto.UserResponse
import com.gitwho.domain.repository.UserRepository

class FakeUserRepository : UserRepository {

    override suspend fun getUserByName(query: String): SearchResponse {
        Log.d("Fake Repo", "getUserByName: " + DummyData.searchResponse)
        return DummyData.searchResponse
    }

    override suspend fun getUserByUsername(username: String): UserResponse {
        return DummyData.userOne
    }
}
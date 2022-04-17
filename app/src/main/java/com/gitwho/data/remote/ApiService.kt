package com.gitwho.data.remote


import com.gitwho.data.remote.dto.SearchResponse
import com.gitwho.data.remote.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the API
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val URL_SEARCH = "search/users"
        const val URL_USER_DETAIL = "users/{username}"
        const val QUERY_NAME = "q"
        const val PATH_USERNAME = "username"
        const val ACCEPT = "Accept"
        const val ACCEPT_TOKEN = "application/vnd.github.v3+json"
    }

    @GET(URL_SEARCH)
    suspend fun getUserByName(
        @Header(ACCEPT) accept: String,
        @Query(QUERY_NAME) query: String
    ): SearchResponse

    @GET(URL_USER_DETAIL)
    suspend fun getUserByUsername(
        @Header(ACCEPT) accept: String,
        @Path(PATH_USERNAME) username: String
    ): UserResponse
}


package com.gitwho.common

import com.gitwho.data.remote.dto.Item
import com.gitwho.data.remote.dto.SearchResponse
import com.gitwho.data.remote.dto.UserResponse
import com.gitwho.data.remote.dto.toUser

object DummyData {

    private val itemOne = Item(
        id = 1,
        avatarUrl = "sample",
        login = "Pratz9999",
        type = "User"
    )
    private val itemTwo = Item(
        id = 2,
        avatarUrl = "sample2",
        login = "Jake",
        type = "User"
    )

    val itemOneResult = itemOne.toUser()
    val itemTwoResult = itemTwo.toUser()

    val userOne = UserResponse(
        id = 1,
        avatarUrl = "sample",
        login = "Pratz9999",
        type = "User",
        publicRepos = 2,
        email = "pratzz.sharma@gmail.com",
        url = "www.google.com",
        followers = 100,
        following = 100,
        name = "Prateek Sharma",
        location = "Delhi, India",
        twitterUsername = "pratz9999"
    )

    val searchResponse =
        SearchResponse(totalCount = 2, incompleteResults = false, listOf(itemOne, itemTwo))

    val searchResultResponse = listOf(itemOneResult, itemTwoResult)
    val usernameResponse = userOne.toUser()
}
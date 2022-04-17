package com.gitwho.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val bio: String? = null,
    val login: String? = null,
    val type: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val id: Int? = null,
    val publicRepos: String? = null,
    val email: String? = null,
    val publicGists: String? = null,
    val url: String? = null,
    val followers: String? = null,
    val avatarUrl: String? = null,
    val following: String? = null,
    val name: String? = null,
    val location: String? = null,
    val twitterUsername: String? = null
) : Parcelable
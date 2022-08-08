package com.testing.githubsearch.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUser(
    val reposUrl: String,
    val gistsUrl: String,
    val followingUrl: String,
    val starredUrl: String,
    val login: String,
    val followersUrl: String,
    val type: String,
    val url: String,
    val subscriptionsUrl: String,
    val score: Int,
    val receivedEventsUrl: String,
    val avatarUrl: String,
    val eventsUrl: String,
    val htmlUrl: String,
    val siteAdmin: Boolean,
    val id: Int,
    val gravatarId: String,
    val nodeId: String,
    val organizationsUrl: String
) : Parcelable, BaseModel

/*
* This is a marker interface. It is used to make common base classes like
* adapters and view holders. Every model to be shown in recycler view must
* inherit it.
* */
interface BaseModel
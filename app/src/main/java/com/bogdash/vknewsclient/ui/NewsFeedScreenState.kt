package com.bogdash.vknewsclient.ui

import com.bogdash.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    object Initial: NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}
package com.bogdash.vknewsclient.ui

import com.bogdash.vknewsclient.domain.FeedPost
import com.bogdash.vknewsclient.domain.PostComment

sealed class HomeScreenState {
    object Initial: HomeScreenState()
    data class Posts(val posts: List<FeedPost>): HomeScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): HomeScreenState()
}
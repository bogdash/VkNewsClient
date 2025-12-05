package com.bogdash.vknewsclient.ui

import com.bogdash.vknewsclient.domain.FeedPost
import com.bogdash.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentsScreenState()
}
package com.bogdash.vknewsclient.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.bogdash.vknewsclient.ViewModel
import com.bogdash.vknewsclient.domain.PostComment
import com.bogdash.vknewsclient.ui.CommentsScreen

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: ViewModel
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())
    if (feedPosts.value.isNotEmpty()) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(20) {
                add(
                    PostComment(id = it)
                )
            }
        }
        CommentsScreen(feedPost = feedPosts.value[0], comments = comments)
    }


//    LazyColumn(
//        contentPadding = PaddingValues(
//            top = paddingValues.calculateTopPadding(),
//            bottom = paddingValues.calculateBottomPadding() + 16.dp,
//            start = 8.dp,
//            end= 8.dp
//        ),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(items = feedPosts.value, key = {it.id}) { feedPost ->
//            val dismissState = rememberSwipeToDismissBoxState(
//                confirmValueChange = { value ->
//                    val isDismissed = value in setOf(
//                        SwipeToDismissBoxValue.EndToStart
//                    )
//
//                    if (isDismissed) {
//                        viewModel.remove(feedPost)
//                    }
//                    return@rememberSwipeToDismissBoxState isDismissed
//                }
//            )
//            SwipeToDismissBox(
//                modifier = Modifier.animateItem(
//                    fadeInSpec = null,
//                    fadeOutSpec = null,
//                    placementSpec = spring(
//                        stiffness = Spring.StiffnessMediumLow,
//                        visibilityThreshold = IntOffset.VisibilityThreshold
//                    )
//                ),
//                state = dismissState,
//                enableDismissFromEndToStart = true,
//                enableDismissFromStartToEnd = false,
//                backgroundContent = {}
//            ) {
//                PostCard(
//                    feedPost = feedPost,
//                    onStatisticsItemClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    }
//                )
//            }
//        }
//    }
}
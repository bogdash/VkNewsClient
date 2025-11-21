package com.bogdash.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogdash.vknewsclient.domain.FeedPost
import com.bogdash.vknewsclient.domain.StatisticItem

class ViewModel: ViewModel() {
    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem) {
        // TODO: look at the type
        val oldStatistics = feedPost.value?.statistics ?: throw IllegalStateException("error in updateCount fun")
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        _feedPost.value = feedPost.value?.copy(statistics = newStatistics)
    }
}
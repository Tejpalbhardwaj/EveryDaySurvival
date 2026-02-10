package com.example.everydaysurvival

import androidx.lifecycle.ViewModel
import com.example.everydaysurvival.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel : ViewModel() {
    private val _savedPosts = MutableStateFlow<List<Post>>(emptyList())
    val savedPosts: StateFlow<List<Post>> = _savedPosts.asStateFlow()

    fun addSavedPost(post: Post) {
        val currentList = _savedPosts.value.toMutableList()
        // Check if post already exists
        if (!currentList.any { it.id == post.id }) {
            currentList.add(post)
            _savedPosts.value = currentList
        }
    }

    fun removeSavedPost(post: Post) {
        val currentList = _savedPosts.value.toMutableList()
        currentList.removeAll { it.id == post.id}
        _savedPosts.value = currentList
    }
}
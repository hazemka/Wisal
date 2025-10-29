package com.app.presentation.screen.home

data class HomeState(
    val posts: List<PostUiModel>  =emptyList(),
    val isLoading: Boolean = false
)

data class PostUiModel(
    val title: String,
    val content: String,
    val summary: String,
    val imageUrl: Int,
    val institutionName: String,
)


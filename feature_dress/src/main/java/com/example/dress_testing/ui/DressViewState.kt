package com.example.dress_testing.ui

import com.example.dress_testing.domain.models.DressEntity

data class DressViewState(
    val dressList: List<DressEntity>? = emptyList(),
    val isLoading: Boolean = false
)

package com.example.bisinesautomatizationtask.features.dress.ui

import com.example.bisinesautomatizationtask.features.dress.domain.models.DressEntity

data class DressViewState(
    val dressList: List<DressEntity>? = emptyList(),
    val isLoading: Boolean = false
)

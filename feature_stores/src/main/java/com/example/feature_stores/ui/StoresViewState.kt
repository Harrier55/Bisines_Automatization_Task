package com.example.feature_stores.ui

import com.example.feature_stores.domain.models.StoresEntity

data class StoresViewState(
    val listStores: List<StoresEntity>   = emptyList(),
    val isLoading: Boolean               = false
)

package com.example.bisinesautomatizationtask.features.stores.ui

import com.example.bisinesautomatizationtask.features.stores.domain.models.StoresEntity

data class StoresViewState(
    val listStores: List<StoresEntity>   = emptyList(),
    val isLoading: Boolean               = false
)

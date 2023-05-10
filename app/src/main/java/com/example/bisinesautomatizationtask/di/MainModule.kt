package com.example.bisinesautomatizationtask.di

import com.example.bisinesautomatizationtask.navigationApi.NavigationFeatureStoresImpl
import com.example.feature_stores.navigationApi.NavigationApiFeatureStores
import org.koin.dsl.module

val mainModule = module {
    single  <NavigationApiFeatureStores>{ NavigationFeatureStoresImpl() }
}
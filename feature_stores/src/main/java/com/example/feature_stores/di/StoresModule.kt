package com.example.feature_stores.di

import com.example.feature_stores.data.StoresRepositoryImpl
import com.example.feature_stores.domain.repositories.StoresRepository
import com.example.feature_stores.domain.usecase.GetListStoresUseCase
import com.example.feature_stores.domain.usecase.GetStoreById
import com.example.feature_stores.vm.StoresViewModel
import org.koin.dsl.module

val storesModule = module {
    single <StoresRepository>{ StoresRepositoryImpl(webConnection = get()) }
    factory { GetListStoresUseCase(storesRepository = get()) }
    factory { GetStoreById(storesRepository = get()) }
    single { StoresViewModel(useCase = get(), navigation = get()) }

}
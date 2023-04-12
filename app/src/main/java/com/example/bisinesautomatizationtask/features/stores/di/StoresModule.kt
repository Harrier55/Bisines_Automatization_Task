package com.example.bisinesautomatizationtask.features.stores.di

import com.example.bisinesautomatizationtask.features.stores.domain.usecase.GetListStoresUseCase
import com.example.bisinesautomatizationtask.features.stores.domain.usecase.GetStoreById
import com.example.bisinesautomatizationtask.features.stores.vm.StoresViewModel
import org.koin.dsl.module

val storesModule = module {
    factory { GetListStoresUseCase(storesRepository = get()) }
    factory { GetStoreById(storesRepository = get()) }
    single { StoresViewModel(useCase = get()) }

}
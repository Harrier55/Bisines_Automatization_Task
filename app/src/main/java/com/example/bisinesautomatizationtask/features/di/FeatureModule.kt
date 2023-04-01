package com.example.bisinesautomatizationtask.features.di

import com.example.bisinesautomatizationtask.features.dress.domain.usecase.GetDressListUseCase
import com.example.bisinesautomatizationtask.features.dress.vm.DressViewModel
import com.example.bisinesautomatizationtask.features.stores.domain.usecase.GetListStoresUseCase
import com.example.bisinesautomatizationtask.features.stores.vm.StoresViewModel
import org.koin.dsl.module

val homeModule = module {

    factory { GetDressListUseCase(dressRepository = get()) }
    factory { GetListStoresUseCase(storesRepository = get()) }

    single { DressViewModel(useCase = get()) }
    single { StoresViewModel(useCase = get()) }
}
package com.example.bisinesautomatizationtask.features.dress.di

import com.example.bisinesautomatizationtask.features.dress.domain.usecase.GetDressListUseCase
import com.example.bisinesautomatizationtask.features.dress.vm.DressViewModel
import org.koin.dsl.module

val dressModule = module {
    factory { GetDressListUseCase(dressRepository = get()) }
    single { DressViewModel(useCase = get()) }


}
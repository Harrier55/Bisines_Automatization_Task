package com.example.dress_testing.di

import com.example.dress_testing.data.DressRepositoryImpl
import com.example.dress_testing.domain.repository.DressRepository
import com.example.dress_testing.domain.usecase.GetDressListUseCase
import com.example.dress_testing.vm.DressViewModel
import org.koin.dsl.module

val dressModule = module {
    single<DressRepository> { DressRepositoryImpl(webConnection = get()) }
    factory { GetDressListUseCase(dressRepository = get()) }
    single { DressViewModel(useCase = get()) }


}
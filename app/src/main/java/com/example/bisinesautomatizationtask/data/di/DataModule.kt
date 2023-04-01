package com.example.bisinesautomatizationtask.data.di

import com.example.bisinesautomatizationtask.data.DressRepositoryImpl
import com.example.bisinesautomatizationtask.data.StoresRepositoryImpl
import com.example.bisinesautomatizationtask.data.datasourse.WebConnection
import com.example.bisinesautomatizationtask.features.dress.domain.repository.DressRepository
import com.example.bisinesautomatizationtask.features.stores.domain.repositories.StoresRepository
import org.koin.dsl.module

val dataModule = module {
    single<DressRepository> { DressRepositoryImpl(webConnection = get()) }

    single <StoresRepository>{ StoresRepositoryImpl(webConnection = get()) }

    single { WebConnection() }

}
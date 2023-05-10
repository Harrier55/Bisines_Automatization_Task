package com.example.datasourse.di

import com.example.datasourse.datasourse.WebConnection

import org.koin.dsl.module

val dataModule = module {
    single { WebConnection() }

}
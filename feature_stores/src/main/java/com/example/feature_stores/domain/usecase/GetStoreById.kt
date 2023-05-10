package com.example.feature_stores.domain.usecase

import com.example.feature_stores.domain.models.StoresEntity
import com.example.feature_stores.domain.repositories.StoresRepository

class GetStoreById(private val storesRepository: StoresRepository) {

    fun execute(id: Double): StoresEntity?{
       return storesRepository.getStoreById(id = id)
    }
}
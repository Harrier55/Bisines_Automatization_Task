package com.example.bisinesautomatizationtask.features.stores.domain.usecase

import com.example.bisinesautomatizationtask.features.stores.domain.models.StoresEntity
import com.example.bisinesautomatizationtask.features.stores.domain.repositories.StoresRepository

class GetStoreById(private val storesRepository: StoresRepository) {

    fun execute(id: Double): StoresEntity?{
       return storesRepository.getStoreById(id = id)
    }
}
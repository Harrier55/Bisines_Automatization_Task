package com.example.bisinesautomatizationtask.features.stores.domain.repositories

import com.example.bisinesautomatizationtask.data.models.RepositoryResult
import com.example.bisinesautomatizationtask.features.stores.domain.models.StoresEntity


interface StoresRepository {

    fun getLocalData(): List<StoresEntity>
    suspend fun getSourceData(): RepositoryResult<List<StoresEntity>>
    fun update(inputList: List<StoresEntity>)
    fun getStoreById(id: Double): StoresEntity?
}
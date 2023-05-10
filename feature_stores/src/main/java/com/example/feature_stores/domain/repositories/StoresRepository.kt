package com.example.feature_stores.domain.repositories


import com.example.feature_stores.data.RepositoryResultStores
import com.example.feature_stores.domain.models.StoresEntity


interface StoresRepository {

    fun getLocalData(): List<StoresEntity>
    suspend fun getSourceData(): RepositoryResultStores<List<StoresEntity>>
    fun update(inputList: List<StoresEntity>)
    fun getStoreById(id: Double): StoresEntity?
}
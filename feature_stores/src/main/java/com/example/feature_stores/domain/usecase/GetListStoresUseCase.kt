package com.example.feature_stores.domain.usecase

import com.example.core_module.LocalResult
import com.example.feature_stores.domain.repositories.StoresRepository
import kotlinx.coroutines.flow.flow

class GetListStoresUseCase(private val storesRepository: StoresRepository) {

    suspend fun execute() = flow {

        val localData =  storesRepository.getLocalData()

        if (localData.isNotEmpty()){
            emit(LocalResult.Success(_data = localData))
        }else {
            val sourceData = storesRepository.getSourceData()
            when (sourceData.status) {
                "SUCCESS" -> {
                    emit(LocalResult.Success(_data = sourceData.data))
                    sourceData.data?.let { storesRepository.update(inputList = it) }
                }
                "ERROR" -> {
                    emit(LocalResult.Error(exception = sourceData.message!!))
                }
            }
        }
    }
}
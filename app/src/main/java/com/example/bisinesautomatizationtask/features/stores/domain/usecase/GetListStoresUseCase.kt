package com.example.bisinesautomatizationtask.features.stores.domain.usecase

import com.example.bisinesautomatizationtask.core.LocalResult
import com.example.bisinesautomatizationtask.data.models.RepositoryStatus
import com.example.bisinesautomatizationtask.features.stores.domain.repositories.StoresRepository
import kotlinx.coroutines.flow.flow

class GetListStoresUseCase(private val storesRepository: StoresRepository) {

    suspend fun execute() = flow {

        val localData =  storesRepository.getLocalData()

        if (localData.isNotEmpty()){
            emit(LocalResult.Success(_data = localData))
        }else {
            val sourceData = storesRepository.getSourceData()
            when (sourceData.status) {
                RepositoryStatus.SUCCESS -> {
                    emit(LocalResult.Success(_data = sourceData.data))
                    sourceData.data?.let { storesRepository.update(inputList = it) }
                }
                RepositoryStatus.ERROR -> {
                    emit(LocalResult.Error(exception = sourceData.message!!))
                }
            }
        }
    }
}
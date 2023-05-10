package com.example.dress_testing.domain.usecase

import android.annotation.SuppressLint
import com.example.core_module.LocalResult
import com.example.dress_testing.domain.models.DressEntity
import com.example.dress_testing.domain.repository.DressRepository
import kotlinx.coroutines.flow.flow

class GetDressListUseCase(private val dressRepository: DressRepository) {

    @SuppressLint("SuspiciousIndentation")
    fun execute() = flow<LocalResult<List<DressEntity>>>{

     val localData =  dressRepository.getLocalData()

        if(localData.isNotEmpty()){
            emit(LocalResult.Success(_data = localData))
        }else{
            val sourceData =   dressRepository.getSourceData()
            when(sourceData.status){
                "SUCCESS" ->{
                    emit(LocalResult.Success(_data = sourceData.data))
                    sourceData.data?.let { dressRepository.save(inputList = it) }
                }
                "ERROR" -> {
                    emit(LocalResult.Error(exception = sourceData.message!!))
                }
            }
        }
    }
}
package com.example.bisinesautomatizationtask.features.dress.domain.usecase

import android.annotation.SuppressLint
import com.example.bisinesautomatizationtask.core.LocalResult
import com.example.bisinesautomatizationtask.features.dress.domain.repository.DressRepository
import kotlinx.coroutines.flow.flow

class GetDressListUseCase(private val dressRepository: DressRepository) {

    @SuppressLint("SuspiciousIndentation")
    fun execute() = flow{

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
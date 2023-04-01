package com.example.bisinesautomatizationtask.features.dress.domain.repository

import com.example.bisinesautomatizationtask.data.datasourse.DressSourceEntity
import com.example.bisinesautomatizationtask.data.models.RepositoryResult
import com.example.bisinesautomatizationtask.features.dress.domain.models.DressEntity

interface DressRepository {

    fun getLocalData(): List<DressEntity>
    suspend fun getSourceData(): RepositoryResult<List<DressEntity>>
    fun save(inputList: List<DressEntity>)
}
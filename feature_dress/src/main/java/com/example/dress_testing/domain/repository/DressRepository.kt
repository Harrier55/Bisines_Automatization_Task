package com.example.dress_testing.domain.repository

import com.example.dress_testing.data.RepositoryResult
import com.example.dress_testing.domain.models.DressEntity

interface DressRepository {

    fun getLocalData(): List<DressEntity>
    suspend fun getSourceData(): RepositoryResult<List<DressEntity>>
    fun save(inputList: List<DressEntity>)
}
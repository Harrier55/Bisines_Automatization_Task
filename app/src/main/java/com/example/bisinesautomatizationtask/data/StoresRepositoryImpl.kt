package com.example.bisinesautomatizationtask.data

import com.example.bisinesautomatizationtask.data.datasourse.ApiStatus
import com.example.bisinesautomatizationtask.data.datasourse.StoriesSourceEntity
import com.example.bisinesautomatizationtask.data.datasourse.WebConnection
import com.example.bisinesautomatizationtask.data.models.RepositoryResult
import com.example.bisinesautomatizationtask.features.stores.domain.repositories.StoresRepository
import com.example.bisinesautomatizationtask.features.stores.domain.models.StoresEntity

class StoresRepositoryImpl(private val webConnection: WebConnection) : StoresRepository {

    private val cashListStoriesEntity = mutableListOf<StoresEntity>()

    override fun getLocalData(): List<StoresEntity> {
        return cashListStoriesEntity
    }

    override suspend fun getSourceData(): RepositoryResult<List<StoresEntity>> {

        val resultSource = webConnection.getStoresList()
        return when (resultSource.status) {
            ApiStatus.SUCCESS -> {
                val dataList = resultSource.data
                RepositoryResult.Success(_data = dataList?.let {
                    mapSourceEntityToStoresEntity(it)
                })
            }
            ApiStatus.ERROR -> {
                RepositoryResult.Error(exception = resultSource.message!!)
            }
        }
    }

    override fun update(inputList: List<StoresEntity>) {
        //  TODO("Not yet implemented")
    }

    private fun mapSourceEntityToStoresEntity(inputList: List<StoriesSourceEntity>): List<StoresEntity> {
        val outputList = mutableListOf<StoresEntity>()
        inputList.forEach { storiesSourceEntity ->
            outputList.add(
                StoresEntity(
                    name = storiesSourceEntity.name,
                    longitude = storiesSourceEntity.longitude,
                    latitude = storiesSourceEntity.latitude,
                    address = storiesSourceEntity.address
                )
            )
        }
        return outputList
    }

}
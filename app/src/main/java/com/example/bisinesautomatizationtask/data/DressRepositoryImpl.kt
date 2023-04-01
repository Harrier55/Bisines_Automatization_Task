package com.example.bisinesautomatizationtask.data

import com.example.bisinesautomatizationtask.data.datasourse.ApiStatus
import com.example.bisinesautomatizationtask.data.datasourse.DressSourceEntity
import com.example.bisinesautomatizationtask.data.datasourse.WebConnection
import com.example.bisinesautomatizationtask.data.models.RepositoryResult
import com.example.bisinesautomatizationtask.features.dress.domain.models.DressEntity
import com.example.bisinesautomatizationtask.features.dress.domain.repository.DressRepository


class DressRepositoryImpl(private val webConnection: WebConnection) : DressRepository {

    private var cashListDressEntity = mutableListOf<DressEntity>()

    override fun getLocalData(): List<DressEntity> {
        return cashListDressEntity
    }

    override suspend fun getSourceData(): RepositoryResult<List<DressEntity>> {

        val resultSource = webConnection.getDressList()
        when (resultSource.status) {
            ApiStatus.SUCCESS -> {
                val dataList = resultSource.data
                return RepositoryResult.Success(_data = dataList?.let {
                    mapSourceEntityToDressEntity(
                        it
                    )
                })
            }
            ApiStatus.ERROR -> {
                return RepositoryResult.Error(exception = resultSource.message!!)
            }
        }
    }

    override fun save(inputList: List<DressEntity>){
        cashListDressEntity = inputList as MutableList<DressEntity>
    }

    private fun mapSourceEntityToDressEntity(inputList: List<DressSourceEntity>): List<DressEntity> {
        val outputList = mutableListOf<DressEntity>()
        inputList.forEach { dressSourceEntity ->
            outputList.add(
                DressEntity(
                    name = dressSourceEntity.name,
                    id = dressSourceEntity.id,
                    price = dressSourceEntity.prisce,
                    picture = dressSourceEntity.picture
                )
            )
        }
        return outputList
    }
}
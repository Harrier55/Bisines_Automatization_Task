package com.example.datasourse.datasourse

import com.example.datasourse.datasourse.models_dto.DressSourceDTO
import com.example.datasourse.datasourse.models_dto.StoriesSourceDTO
import retrofit2.HttpException
import java.io.IOException

class WebConnection {

    private val api by lazy { RetrofitApi.getApi() }
    private val headers = mapOf("1CSVC" to "PeplosSVC#2022")

    suspend fun getStoresList(): ApiResult<List<StoriesSourceDTO>> {
        return try {
            val response = api.getStores(headers)
//             Log.d("@@@", "getStoresList: OK  ${response.body()}")
            if (response.code() == 200 && response.isSuccessful) {
                val data = response.body()
                ApiResult.Success(_data = data)
            } else {
                ApiResult.Error(exception = "server response ${response.code()}")
            }
        } catch (e: HttpException) {
            ApiResult.Error(exception = "HttpException $e")
        } catch (e: IOException) {
            ApiResult.Error(exception = "IOException $e")
        } catch (e: Exception) {
            ApiResult.Error(exception = "Exception $e")
        }
    }

    suspend fun getDressList(): ApiResult<List<DressSourceDTO>> {
        return try {
            val response = api.getDress(headers)
            if (response.code() == 200 && response.isSuccessful) {
                val data = response.body()
                ApiResult.Success(_data = data)
            } else {
                ApiResult.Error(exception = "server response ${response.code()}")
            }
        } catch (e: HttpException) {
            ApiResult.Error(exception = "HttpException $e")
        } catch (e: IOException) {
            ApiResult.Error(exception = "IOException $e")
        } catch (e: Exception) {
            ApiResult.Error(exception = "Exception $e")
        }
    }


}
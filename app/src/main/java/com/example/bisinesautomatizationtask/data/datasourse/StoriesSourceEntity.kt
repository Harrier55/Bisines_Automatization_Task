package com.example.bisinesautomatizationtask.data.datasourse

import com.google.gson.annotations.SerializedName

data class StoriesSourceEntity(
    @SerializedName("Наименование" ) var name           : String? = null,
    @SerializedName("Долгота"      ) var longitude      : Double? = null,
    @SerializedName("Широта"       ) var latitude       : Double? = null,
    @SerializedName("Адрес"        ) var address        : String? = null
)

package com.example.datasourse.datasourse.models_dto

import com.google.gson.annotations.SerializedName

data class StoriesSourceDTO(
    @SerializedName("Наименование" ) var name           : String? = null,
    @SerializedName("Долгота"      ) var longitude      : Double? = null,
    @SerializedName("Широта"       ) var latitude       : Double? = null,
    @SerializedName("Адрес"        ) var address        : String? = null
)

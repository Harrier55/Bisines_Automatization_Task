package com.example.datasourse.datasourse.models_dto

import com.google.gson.annotations.SerializedName

data class DressSourceDTO(
    @SerializedName("Наименование" ) var name    : String? = null,
    @SerializedName("Ссылка"       ) var id      : String? = null,
    @SerializedName("Цена"         ) var prisce  : String? = null,
    @SerializedName("Картинка"     ) var picture : String? = null
)

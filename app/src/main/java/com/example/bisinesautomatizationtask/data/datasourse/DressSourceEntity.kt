package com.example.bisinesautomatizationtask.data.datasourse

import com.google.gson.annotations.SerializedName

data class DressSourceEntity(
    @SerializedName("Наименование" ) var name    : String? = null,
    @SerializedName("Ссылка"       ) var id      : String? = null,
    @SerializedName("Цена"         ) var prisce  : String? = null,
    @SerializedName("Картинка"     ) var picture : String? = null
)

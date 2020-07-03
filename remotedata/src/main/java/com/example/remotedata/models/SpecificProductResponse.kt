package com.example.remotedata.models

import com.google.gson.annotations.SerializedName

data class SpecificProductResponse(
    val price: Double,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    val pictures: List<Picture>,
    val warranty: String,
    val tags: List<String>
) {
    data class Picture(
        val id: String,
        val url: String
    )
}
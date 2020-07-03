package com.example.remotedata.models

import com.google.gson.annotations.SerializedName

data class ProductsQueryResponse(
    val query: String,
    val results: List<ProductPreviewResponse>
) {
    data class ProductPreviewResponse(
        val id: String,
        @SerializedName("catalog_product_id")
        val catalogProductId: String,
        val title: String,
        val price: Double,
        @SerializedName("currency_id")
        val currencyId: String,
        val condition: String,
        val thumbnail: String,
        val shipping: Shipping
    ) {
        data class Shipping(
            val freeShipping: Boolean
        )
    }
}

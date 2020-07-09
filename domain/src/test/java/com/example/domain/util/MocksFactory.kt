package com.example.domain.util

import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview

object MocksFactory {
    fun getFakeProductDetail(): ProductDetail {
        return ProductDetail(
            title = "Fake title",
            price = 1000F,
            images = listOf("fake url"),
            warranty = "fake warranty",
            availableQuantity = 100,
            soldQuantity = 100,
            tags = listOf("fake tag"),
            description = "fake description",
            features = listOf(ProductDetail.Feature("fake feature", "fake type"))
        )
    }

    fun getFakeProductsPreview(): List<ProductPreview> {
        return (0..10).map { index ->
            ProductPreview(
                id = index.toString(),
                title = "Fake product number $index",
                price = (index * 1000).toDouble(),
                thumbnailUrl = "fake url",
                currency = "USD",
                hasFreeShipping = true
            )
        }
    }
}
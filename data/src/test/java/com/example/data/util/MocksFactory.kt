package com.example.data.util

import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview
import com.example.remotedata.models.FeaturesAndDescriptionResponse
import com.example.remotedata.models.ProductsQueryResponse
import com.example.remotedata.models.SpecificProductResponse

object MocksFactory {
    // region api model fake response
    fun createFakeProductQueryResponse(query: String): ProductsQueryResponse {
        return ProductsQueryResponse(
            query = query,
            results = getFakeProductPreviewResponse()

        )
    }

    fun createFakeProductDetailResponse(catalogProductId: String): SpecificProductResponse {
        return SpecificProductResponse(
            title = "Fake title",
            catalogProductId = catalogProductId,
            price = 1000.toDouble(),
            currencyId = "USD",
            availableQuantity = 100,
            soldQuantity = 100,
            pictures = listOf(SpecificProductResponse.Picture("fakeImageId", "FakeImageUrl")),
            warranty = "fake warranty",
            tags = listOf("tags")
        )
    }

    fun createFakeFeaturesAndDescription(): FeaturesAndDescriptionResponse {
        return FeaturesAndDescriptionResponse(
            id = "fake id",
            shortDescription = FeaturesAndDescriptionResponse.ShortDescription("fake type", "fake description"),
            features = listOf(FeaturesAndDescriptionResponse.Features("fake type feature", "fake feature"))
        )
    }

    private fun getFakeProductPreviewResponse(): List<ProductsQueryResponse.ProductPreviewResponse> {
        return (0..10).map { index ->
            ProductsQueryResponse.ProductPreviewResponse(
                id = index.toString(),
                catalogProductId = index.toString(),
                title = "Fake Product number $index",
                price = (index * 1000).toDouble(),
                currencyId = "USD",
                condition = "new",
                thumbnail = "Fake url",
                shipping = ProductsQueryResponse.ProductPreviewResponse.Shipping(true)
            )
        }
    }
    // endregion

    // region fake domain models

    fun getFakeProductPreview(): List<ProductPreview> {
        return (0..10).map { index ->
            ProductPreview(
                id = index.toString(),
                title = "Product number $index",
                price = (index * 1000).toDouble(),
                thumbnailUrl = "Fake url",
                currency = "USD",
                hasFreeShipping = true
            )
        }
    }

    fun getFakeProductDetail(): ProductDetail {
        return ProductDetail(
            title = "fake title",
            price = 1000F,
            images = listOf("Fake url"),
            warranty = "fake warranty",
            availableQuantity = 100,
            soldQuantity = 100,
            tags = listOf("fake tag"),
            description = "fake description",
            features = listOf(ProductDetail.Feature("fake feature", "fake type"))
        )
    }

    // endregion
}
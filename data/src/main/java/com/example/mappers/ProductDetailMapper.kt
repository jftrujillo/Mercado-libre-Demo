package com.example.mappers

import com.example.domain.model.ProductDetail
import com.example.remotedata.models.FeaturesAndDescriptionResponse
import com.example.remotedata.models.SpecificProductResponse
import javax.inject.Inject

class ProductDetailMapper @Inject constructor() {
    operator fun invoke(
        specificProductResponse: SpecificProductResponse,
        featuresAndDescriptionResponse: FeaturesAndDescriptionResponse?
    ): ProductDetail {
        return ProductDetail(
            title = specificProductResponse.title,
            price = specificProductResponse.price.toFloat(),
            images = specificProductResponse.pictures.map { it.url },
            warranty = specificProductResponse.warranty,
            availableQuantity = specificProductResponse.availableQuantity,
            soldQuantity = specificProductResponse.soldQuantity,
            description = featuresAndDescriptionResponse?.shortDescription?.content,
            features = featuresAndDescriptionResponse?.features?.map {
                ProductDetail.Feature(
                    it.text,
                    it.type
                )
            },
            tags = specificProductResponse.tags
        )
    }
}
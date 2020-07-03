package com.example.mappers

import com.example.domain.model.ProductPreview
import com.example.remotedata.models.ProductsQueryResponse
import javax.inject.Inject

class ProductPreviewMapper @Inject constructor() {
    operator fun invoke(productResponse: ProductsQueryResponse.ProductPreviewResponse): ProductPreview {
        productResponse.apply {
            return ProductPreview(
                id = id,
                price = price,
                title = title,
                thumbnailUrl = thumbnail,
                currency = currencyId,
                hasFreeShipping = shipping.freeShipping
            )
        }
    }
}

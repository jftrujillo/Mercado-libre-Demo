package com.example.datasources.impl

import com.example.datasources.RemoteDataSource
import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview
import com.example.mappers.ProductDetailMapper
import com.example.mappers.ProductPreviewMapper
import com.example.remotedata.api.ProductsApi
import com.example.remotedata.models.FeaturesAndDescriptionResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val productDetailMapper: ProductDetailMapper,
    private val productPreviewMapper: ProductPreviewMapper,
    private val productsApi: ProductsApi
) : RemoteDataSource {

    override suspend fun getProducts(query: String): List<ProductPreview> {
        val response = productsApi.getProductsByQuery(query = query)
        return response.results.map { productPreview ->
            productPreviewMapper.invoke(productPreview)
        }
    }

    override suspend fun getProductDetail(productId: String): ProductDetail {
        val response = productsApi.getSpecificProductResponse(productId)
        var featuresAndDescriptionResponse: FeaturesAndDescriptionResponse? = null
        response.catalogProductId?.let {
            featuresAndDescriptionResponse = productsApi.getFeaturesAndDescriptions(it)
        }
        return productDetailMapper.invoke(response, featuresAndDescriptionResponse)
    }
}
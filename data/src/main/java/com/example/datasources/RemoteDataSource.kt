package com.example.datasources

import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview

interface RemoteDataSource {
    suspend fun getProducts(query: String): List<ProductPreview>
    suspend fun getProductDetail(productId: String): ProductDetail
}
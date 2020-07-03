package com.example.domain
import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview
import com.example.domain.util.Result

interface ProductsRepository {
    suspend fun getProductsPreview(query: String): Result<List<ProductPreview>>
    suspend fun getProductDetails(productId: String): Result<ProductDetail>
}
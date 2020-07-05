package com.example.domain.usecase

import com.example.domain.ProductsRepository
import com.example.domain.model.ProductDetail
import com.example.domain.util.Result

class GetProductDetailUseCase (
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(productId: String): Result<ProductDetail> {
        return productsRepository.getProductDetails(productId)
    }
}
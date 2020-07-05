package com.example.domain.usecase

import com.example.domain.ProductsRepository
import com.example.domain.model.ProductPreview
import com.example.domain.util.Result

class GetProductsUseCase (
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(query: String) : Result<List<ProductPreview>> {
        return productsRepository.getProductsPreview(query)
    }
}
package com.example.presentation.di

import com.example.domain.ProductsRepository
import com.example.domain.usecase.GetProductDetailUseCase
import com.example.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCasesModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideGEtProductDetailUseCase(productsRepository: ProductsRepository): GetProductDetailUseCase {
        return GetProductDetailUseCase(productsRepository)
    }
}
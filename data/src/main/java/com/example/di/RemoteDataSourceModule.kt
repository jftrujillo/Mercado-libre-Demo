package com.example.di

import com.example.datasources.RemoteDataSource
import com.example.datasources.impl.RemoteDataSourceImpl
import com.example.domain.ProductsRepository
import com.example.repositories.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindsRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ) : RemoteDataSource

    @Binds
    abstract fun bindsProductRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ) : ProductsRepository
}
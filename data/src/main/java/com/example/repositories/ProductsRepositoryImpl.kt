package com.example.repositories

import android.util.Log
import com.example.datasources.RemoteDataSource
import com.example.domain.ProductsRepository
import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview
import com.example.domain.util.ErrorType
import com.example.domain.util.Result
import java.io.IOException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ProductsRepository {

    override suspend fun getProductsPreview(query: String): Result<List<ProductPreview>> {
        return try {
            val remoteProductsPreview = remoteDataSource.getProducts(query)
            Result.Success(data = remoteProductsPreview)
        } catch (exception: IOException) {
            Log.e(this::javaClass.name, exception.message.toString())
            Result.Error(exception = ErrorType.NetworkError)
        } catch (exception: Exception) {
            Log.e(this::javaClass.name, exception.message.toString())
            Result.Error(exception = ErrorType.UnknownError)
        }
    }

    override suspend fun getProductDetails(productId: String): Result<ProductDetail> {
        return try {
            val remoteProduceDetails = remoteDataSource.getProductDetail(productId)
            Result.Success(remoteProduceDetails)
        } catch (exception: IOException) {
            Log.e(this::javaClass.name, exception.message.toString())
            Result.Error(exception = ErrorType.NetworkError)
        } catch (exception: Exception) {
            Log.e(this::javaClass.name, exception.message.toString())
            Result.Error(exception = ErrorType.UnknownError)
        }
    }
}
package com.example.data.repositories

import com.example.data.util.MocksFactory
import com.example.datasources.RemoteDataSource
import com.example.domain.util.ErrorType
import com.example.domain.util.Result
import com.example.repositories.ProductsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProductRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        productsRepositoryImpl = ProductsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `when getProductPreview is invoked and successful retrieve Success state and list of productPreview object`() {
        runBlocking {
            // Given
            val fakeQuery = "fake query"
            `when`(remoteDataSource.getProducts(query = fakeQuery)).thenReturn(MocksFactory.getFakeProductPreview())

            // When
            val resultProductPreview = productsRepositoryImpl.getProductsPreview(fakeQuery)

            // Then
            assert(resultProductPreview is Result.Success)
            assert((resultProductPreview as Result.Success).data == MocksFactory.getFakeProductPreview())
        }
    }

    @Test
    fun `when getProductPreview is invoked and an error occurs and exception is retrieve and it should be controlled`() {
        runBlocking {
            // Given
            val fakeQuery = "fake query"
            `when`(remoteDataSource.getProducts(query = fakeQuery)).thenThrow(IllegalStateException())

            // When
            val resultProductPreview = productsRepositoryImpl.getProductsPreview(fakeQuery)

            // Then
            assert(resultProductPreview is Result.Error)
            (resultProductPreview as Result.Error).exception == ErrorType.UnknownError
        }
    }

    @Test
    fun `when getProductDetails is invoked and successful retrieve success state and productDetail object`() {
        runBlocking {
            // Given
            val fakeProductId = "fake product id"
            `when`(remoteDataSource.getProductDetail(fakeProductId)).thenReturn(MocksFactory.getFakeProductDetail())

            // When
            val resultProductDetail = productsRepositoryImpl.getProductDetails(fakeProductId)

            // Then
            assert(resultProductDetail is Result.Success)
            assert((resultProductDetail as Result.Success).data == MocksFactory.getFakeProductDetail())
        }
    }

    @Test
    fun `when getProductDetails is invoked and an error occurs and exception is retrieve and it should be controlled`() {
        runBlocking {
            // Given
            val fakeProductId = "fake product id"
            `when`(remoteDataSource.getProductDetail(fakeProductId)).thenThrow(IllegalStateException())

            // When
            val resultProductDetail = productsRepositoryImpl.getProductDetails(fakeProductId)

            // Then
            assert(resultProductDetail is Result.Error)
            (resultProductDetail as Result.Error).exception == ErrorType.UnknownError
        }
    }
}
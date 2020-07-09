package com.example.domain

import com.example.domain.usecase.GetProductDetailUseCase
import com.example.domain.util.ErrorType
import com.example.domain.util.MocksFactory
import com.example.domain.util.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class GetProductDetailUseCaseTest {

    @Mock
    lateinit var productsRepository: ProductsRepository

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductDetailUseCase = GetProductDetailUseCase(productsRepository)
    }

    @Test
    fun `GetProductDetailUseCase is invoked successful and retrieve a productDetail`() {
        runBlocking {
            //Given
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductDetails(fakeProductId)).thenReturn(
                Result.Success(
                    MocksFactory.getFakeProductDetail()
                )
            )

            // When
            val resultProductDetail = getProductDetailUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductDetail is Result.Success)
            assert((resultProductDetail as Result.Success).data == MocksFactory.getFakeProductDetail())
        }
    }

    @Test
    fun `GetProductDetailUseCase is invoked with an network error and an Network Error is retrieve`() {
        runBlocking {
            // When
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductDetails(fakeProductId)).thenReturn(
                Result.Error(
                    ErrorType.NetworkError
                )
            )

            // Given
            val resultProductDetail = getProductDetailUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductDetail is Result.Error)
            assert((resultProductDetail as Result.Error).exception == ErrorType.NetworkError)
        }
    }

    @Test
    fun `GetProductDetailUseCase is invoked with an unknown error and an Unknown Error is retrieve`() {
        runBlocking {
            // Given
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductDetails(fakeProductId)).thenReturn(
                Result.Error(
                    ErrorType.UnknownError
                )
            )

            // When
            val resultProductDetail = getProductDetailUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductDetail is Result.Error)
            assert((resultProductDetail as Result.Error).exception == ErrorType.UnknownError)
        }
    }
}

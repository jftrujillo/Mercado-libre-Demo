package com.example.domain

import com.example.domain.usecase.GetProductsUseCase
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
class GetProductsUseCaseTest {

    @Mock
    lateinit var productsRepository: ProductsRepository

    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductsUseCase = GetProductsUseCase(productsRepository)
    }

    @Test
    fun `GetProductsUseCase is invoked successful and retrieve a list of ProductPreview`() {
        runBlocking {
            //Given
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductsPreview(fakeProductId)).thenReturn(
                Result.Success(
                    MocksFactory.getFakeProductsPreview()
                )
            )

            // When
            val resultProductsPreview = getProductsUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductsPreview is Result.Success)
            assert((resultProductsPreview as Result.Success).data == MocksFactory.getFakeProductsPreview())
        }
    }

    @Test
    fun `GetProductsUseCase is invoked with an network error and an Network Error is retrieve`() {
        runBlocking {
            // Given
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductsPreview(fakeProductId)).thenReturn(
                Result.Error(ErrorType.NetworkError)
            )

            // When
            val resultProductsPreview = getProductsUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductsPreview is Result.Error)
            assert((resultProductsPreview as Result.Error).exception is ErrorType.NetworkError)
        }
    }

    @Test
    fun `GetProductsUseCase is invoked with an unknown error and an Unknown Error is retrieve`() {
        runBlocking {
            // Given
            val fakeProductId = "fake product id"
            `when`(productsRepository.getProductsPreview(fakeProductId)).thenReturn(
                Result.Error(ErrorType.UnknownError)
            )

            //When
            val resultProductsPreview = getProductsUseCase.invoke(fakeProductId)

            // Then
            assert(resultProductsPreview is Result.Error)
            assert((resultProductsPreview as Result.Error).exception is ErrorType.UnknownError)
        }
    }
}
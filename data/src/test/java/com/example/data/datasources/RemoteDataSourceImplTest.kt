package com.example.data.datasources

import com.example.data.util.MocksFactory
import com.example.datasources.impl.RemoteDataSourceImpl
import com.example.mappers.ProductDetailMapper
import com.example.mappers.ProductPreviewMapper
import com.example.remotedata.api.ProductsApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {

    @Mock
    lateinit var productsApi: ProductsApi

    private val productDetailMapper = ProductDetailMapper()
    private val productPreviewMapper = ProductPreviewMapper()
    private lateinit var productRemoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        productRemoteDataSource = RemoteDataSourceImpl(
            productsApi = productsApi,
            productDetailMapper = productDetailMapper,
            productPreviewMapper = productPreviewMapper
        )
    }

    @Test
    fun `when getProducts is invoked with correct id a list of ProductPreview is retrieve`() {
        runBlocking {
            // Given
            val query = "Fake query"
            val mockedResponse = MocksFactory.createFakeProductQueryResponse(query)
            `when`(productsApi.getProductsByQuery(query = query)).thenReturn(mockedResponse)

            // When
            val response = productRemoteDataSource.getProducts(query)

            // Then
            val mappedMockedResponse = mockedResponse.results.map {
                productPreviewMapper.invoke(it)
            }
            assert(mappedMockedResponse == response)
        }
    }

    @Test
    fun `when getProductDetail is invoked a correct productDetail is Retrieve`() {
        runBlocking {
            // Given
            val productId = "fake product id"
            val fakeCatalogId = "fake catalog id"
            val detailProductResponseMocked =
                MocksFactory.createFakeProductDetailResponse(fakeCatalogId)
            val featuresAndDescriptionMocked = MocksFactory.createFakeFeaturesAndDescription()
            `when`(productsApi.getSpecificProductResponse(productId)).thenReturn(
                detailProductResponseMocked
            )
            `when`(productsApi.getFeaturesAndDescriptions(fakeCatalogId)).thenReturn(
                featuresAndDescriptionMocked
            )

            // When
            val response = productRemoteDataSource.getProductDetail(productId)

            // Then
            val mappedMockedResponse = productDetailMapper.invoke(
                detailProductResponseMocked,
                featuresAndDescriptionMocked
            )
            assert(response == mappedMockedResponse)
        }
    }

    // Then
    @Test(expected = IllegalStateException::class)
    fun `getProducts method is invoked and an error occurs an exception should be retrieve`() {
        runBlocking {
            // Given
            val query = "fake query"
            `when`(productsApi.getProductsByQuery(query = query)).thenThrow(IllegalStateException())

            // When
            val exceptionResponse = productRemoteDataSource.getProducts(query)
        }
    }

    // Then
    @Test(expected = IllegalStateException::class)
    fun `getProductDetail method is invoked and an error occurs an exception should be retrieve`() {
        runBlocking {
            // Given
            val productId = "fake product id"
            `when`(productsApi.getSpecificProductResponse(productId)).thenThrow(
                IllegalStateException()
            )

            // When
            val exceptionResponse = productRemoteDataSource.getProductDetail(productId)
        }
    }
}
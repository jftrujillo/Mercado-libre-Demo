package com.example.remotedata.api

import com.example.remotedata.models.FeaturesAndDescriptionResponse
import com.example.remotedata.models.ProductsQueryResponse
import com.example.remotedata.models.SpecificProductResponse
import com.example.remotedata.util.Url
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET(Url.SEARCH_PATH)
    suspend fun getProductsByQuery(
        @Path("siteId") site: String = Url.DEFAULT_SITE,
        @Query("q") query: String
    ): ProductsQueryResponse

    @GET(Url.GET_SPECIFIC_PRODUCT_PATH)
    suspend fun getSpecificProductResponse(
        @Path("productId") productId: String
    ) : SpecificProductResponse

    @GET(Url.GET_FEATURES_PATH)
    suspend fun getFeaturesAndDescriptions (
        @Path("catalogProductId") catalogProductId: String
    ) : FeaturesAndDescriptionResponse
}
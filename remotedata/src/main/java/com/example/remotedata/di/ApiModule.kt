package com.example.remotedata.di

import com.example.remotedata.api.ProductsApi
import com.example.remotedata.util.Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {

    @Provides
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .build()
            .create(ProductsApi::class.java)
    }
}
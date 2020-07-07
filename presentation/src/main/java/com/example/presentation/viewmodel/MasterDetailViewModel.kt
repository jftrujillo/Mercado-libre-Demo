package com.example.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductPreview
import com.example.domain.usecase.GetProductDetailUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterDetailViewModel @ViewModelInject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val productLiveData = MutableLiveData<Result<List<ProductPreview>>>()
    private val detailProductLiveData = MutableLiveData<Result<ProductDetail>>()
    fun getProducts(): LiveData<Result<List<ProductPreview>>> = productLiveData
    fun getDetailedProduct(): LiveData<Result<ProductDetail>> = detailProductLiveData

    fun searchBy(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productLiveData.postValue(Result.Loading)
                val products = getProductsUseCase.invoke(query)
                productLiveData.postValue(products)
            }
        }
    }

    fun getProductDetail(productId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                detailProductLiveData.postValue(Result.Loading)
                val detailedProduct = getProductDetailUseCase.invoke(productId)
                detailProductLiveData.postValue(detailedProduct)
            }
        }
    }
}
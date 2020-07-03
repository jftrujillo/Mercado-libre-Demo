package com.example.domain.model

data class ProductPreview(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnailUrl: String,
    val currency: String,
    val hasFreeShipping: Boolean
)
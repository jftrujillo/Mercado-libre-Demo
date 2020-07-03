package com.example.remotedata.models

import com.google.gson.annotations.SerializedName

data class FeaturesAndDescriptionResponse(
    val id: String,
    @SerializedName("short_description")
    val shortDescription: ShortDescription,
    val features: List<Features>
) {

    data class Features(
        val type: String,
        val text: String
    )

    data class ShortDescription(
        val type: String?,
        val content: String?
    )
}

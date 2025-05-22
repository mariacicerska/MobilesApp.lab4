package com.example.hw4.data.remoteDataSource

import com.google.gson.annotations.SerializedName

data class PhoneValidationResponse(
    @SerializedName("is_valid") val isValid: Boolean,
    val country: String?,
    val location: String?,
    val timezone: String?,
    @SerializedName("country_code") val countryCode: String?
)
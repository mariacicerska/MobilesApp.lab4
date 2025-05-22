package com.example.hw4.data.remoteDataSource

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/validatephone")
    suspend fun validatePhone(@Query("number") number: String): PhoneValidationResponse
}
package com.example.hw4.domain.repositories


import com.example.hw4.data.entities.PhoneValidationEntity
import com.example.hw4.data.remoteDataSource.PhoneValidationResponse
import kotlinx.coroutines.flow.Flow

interface PhoneValidationRepository {
    fun validatePhoneNumber(phoneNumber: String): Flow<Result<PhoneValidationResponse>>
    fun getValidationHistory(): Flow<List<PhoneValidationEntity>>
}
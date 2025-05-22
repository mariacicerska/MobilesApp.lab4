package com.example.hw4.domain.useCases.user

import com.example.hw4.data.remoteDataSource.PhoneValidationResponse
import com.example.hw4.domain.repositories.PhoneValidationRepository

import kotlinx.coroutines.flow.Flow

class ValidatePhoneUseCase(private val repository: PhoneValidationRepository) {
    operator fun invoke(phoneNumber: String): Flow<Result<PhoneValidationResponse>> {
        return repository.validatePhoneNumber(phoneNumber)
    }
}
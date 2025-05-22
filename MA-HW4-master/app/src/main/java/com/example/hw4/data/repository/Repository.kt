package com.example.hw4.data.repository

import com.example.hw4.data.entities.PhoneValidationEntity
import com.example.hw4.data.localDataSources.userDataSource.LocalDataSource
import com.example.hw4.data.remoteDataSource.ApiService
import com.example.hw4.data.remoteDataSource.PhoneValidationResponse
import com.example.hw4.domain.repositories.PhoneValidationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class Repository(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : PhoneValidationRepository {
    override fun validatePhoneNumber(phoneNumber: String): Flow<Result<PhoneValidationResponse>> = flow {
        try {
            val response = apiService.validatePhone(phoneNumber)
            emit(Result.success(response))
            localDataSource.insertValidation(
                PhoneValidationEntity(
                    phoneNumber = phoneNumber,
                    isValid = response.isValid,
                    country = response.country,
                    location = response.location,
                    timezone = response.timezone,
                    countryCode = response.countryCode,
                    timestamp = System.currentTimeMillis()
                )
            )
        } catch (e: IOException) {
            emit(Result.failure(Exception("Network error: ${e.message}")))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Error: ${e.message}")))
        }
    }

    override fun getValidationHistory(): Flow<List<PhoneValidationEntity>> {
        return localDataSource.getAllValidations()
    }
}
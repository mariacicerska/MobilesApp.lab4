package com.example.hw4.data.localDataSources.userDataSource

import com.example.hw4.data.dao.PhoneValidationDao
import com.example.hw4.data.entities.PhoneValidationEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: PhoneValidationDao) {
    suspend fun insertValidation(validation: PhoneValidationEntity) {
        dao.insertValidation(validation)
    }

    fun getAllValidations(): Flow<List<PhoneValidationEntity>> {
        return dao.getAllValidations()
    }
}
package com.example.hw4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hw4.data.entities.PhoneValidationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneValidationDao {
    @Insert
    suspend fun insertValidation(validation: PhoneValidationEntity)

    @Query("SELECT * FROM phone_validations ORDER BY timestamp DESC")
    fun getAllValidations(): Flow<List<PhoneValidationEntity>>
}
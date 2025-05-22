package com.example.hw4.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_validations")
data class PhoneValidationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val phoneNumber: String,
    val isValid: Boolean,
    val country: String?,
    val location: String?,
    val timezone: String?,
    val countryCode: String?,
    val timestamp: Long
)
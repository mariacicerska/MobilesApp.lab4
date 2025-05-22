package com.example.hw4.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw4.data.dao.PhoneValidationDao
import com.example.hw4.data.entities.PhoneValidationEntity

@Database(entities = [PhoneValidationEntity::class], version = 1)
abstract class PhoneValidationDatabase : RoomDatabase() {
    abstract fun phoneValidationDao(): PhoneValidationDao
}
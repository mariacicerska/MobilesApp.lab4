package com.example.hw4.data.koinModules

import android.content.Context
import androidx.room.Room
import com.example.hw4.data.database.PhoneValidationDatabase
import com.example.hw4.data.localDataSources.userDataSource.LocalDataSource
import com.example.hw4.data.remoteDataSource.ApiService
import com.example.hw4.data.repository.Repository
import com.example.hw4.domain.repositories.PhoneValidationRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val DataModule = module {
    single {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("X-RapidAPI-Key", "your_api")
                    .header("X-RapidAPI-Host", "validate-phone-by-api-ninjas.p.rapidapi.com")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        Retrofit.Builder()
            .baseUrl("https://validate-phone-by-api-ninjas.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    single {
        provideDatabase(get())
    }

    single {
        get<PhoneValidationDatabase>().phoneValidationDao()
    }

    single {
        LocalDataSource(get())
    }

    single<PhoneValidationRepository> {
        Repository(get(), get())
    }
}

private fun provideDatabase(context: Context): PhoneValidationDatabase {
    return Room.databaseBuilder(
        context,
        PhoneValidationDatabase::class.java,
        "phone_validation_db"
    ).build()
}
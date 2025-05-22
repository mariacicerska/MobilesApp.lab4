package com.example.hw4.domain.koinModules


import com.example.hw4.domain.useCases.user.ValidatePhoneUseCase
import org.koin.dsl.module

val DomainModule = module {
    factory { ValidatePhoneUseCase(get()) }
}
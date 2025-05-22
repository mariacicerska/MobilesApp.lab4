package com.example.hw4.presentation.koinModules

import com.example.hw4.presentation.viewModels.PhoneValidationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    viewModel { PhoneValidationViewModel(get()) }
}
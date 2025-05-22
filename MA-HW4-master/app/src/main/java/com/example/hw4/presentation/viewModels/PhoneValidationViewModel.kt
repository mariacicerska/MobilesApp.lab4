package com.example.hw4.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw4.data.remoteDataSource.PhoneValidationResponse
import com.example.hw4.domain.useCases.user.ValidatePhoneUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.getOrThrow

sealed class ValidationState {
    object Idle : ValidationState()
    object Loading : ValidationState()
    data class Success(val response: PhoneValidationResponse) : ValidationState()
    data class Error(val message: String) : ValidationState()
}

class PhoneValidationViewModel(
    private val validatePhoneUseCase: ValidatePhoneUseCase
) : ViewModel() {
    private val _validationState = MutableStateFlow<ValidationState>(ValidationState.Idle)
    val validationState: StateFlow<ValidationState> = _validationState.asStateFlow()

    fun validatePhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            _validationState.value = ValidationState.Loading
            validatePhoneUseCase(phoneNumber).collect { result ->
                _validationState.value = when {
                    result.isSuccess -> ValidationState.Success(result.getOrThrow())
                    else -> ValidationState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
        }
    }
}
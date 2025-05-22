package com.example.hw4.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hw4.presentation.viewModels.PhoneValidationViewModel
import com.example.hw4.presentation.viewModels.ValidationState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: PhoneValidationViewModel) {
    val validationState by viewModel.validationState.collectAsState()
    var phoneNumber by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Enter phone number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (phoneNumber.isNotEmpty()) {
                            viewModel.validatePhoneNumber(phoneNumber)
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Please enter a phone number")
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Validate")
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (val state = validationState) {
                    is ValidationState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    is ValidationState.Success -> {
                        Column {
                            Text("Valid: ${state.response.isValid}")
                            Text("Country: ${state.response.country ?: "N/A"}")
                            Text("Country Code: ${state.response.countryCode ?: "N/A"}")
                            Text("Timezone: ${state.response.timezone ?: "N/A"}")
                            Text(
                                if (state.response.location != state.response.country && !state.response.location.isNullOrEmpty()) {
                                    "Location: ${state.response.location}"
                                } else {
                                    "Location: N/A"
                                }
                            )
                        }
                    }
                    is ValidationState.Error -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = state.message,
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    if (phoneNumber.isNotEmpty()) {
                                        viewModel.validatePhoneNumber(phoneNumber)
                                    }
                                }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                    is ValidationState.Idle -> {}
                }
            }
        }
    )
}
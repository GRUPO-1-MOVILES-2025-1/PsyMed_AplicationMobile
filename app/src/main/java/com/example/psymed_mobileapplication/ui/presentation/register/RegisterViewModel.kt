package com.example.psymed_mobileapplication.ui.presentation.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psymed_mobileapplication.ui.shared.RetrofitClient
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val registrationState = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun registerUser(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                Log.d("RegisterViewModel", "Iniciando registro con datos: $request")
                val response = RetrofitClient.authService.register(request)
                if (response.isSuccessful) {
                    Log.d("RegisterViewModel", "Registro exitoso")
                    registrationState.value = true
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.value = "Error: ${response.code()} - $errorBody"
                    Log.e("RegisterViewModel", "Error en el registro: $errorBody")
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
                Log.e("RegisterViewModel", "Excepción durante el registro: ${e.message}")
            }
        }
    }
}
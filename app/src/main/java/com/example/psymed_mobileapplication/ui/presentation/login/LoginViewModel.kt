package com.example.psymed_mobileapplication.ui.presentation.login

import com.example.psymed_mobileapplication.ui.presentation.register.RegisterRequest


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.psymed_mobileapplication.ui.shared.RetrofitClient
import kotlinx.coroutines.launch

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val error: String?
)

class LoginViewModel : ViewModel() {
    val loginState = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun loginUser(request: LoginRequest, navController: NavController) {
        viewModelScope.launch {
            try {
                Log.d("LoginViewModel", "Iniciando login con datos: $request")
                val response = RetrofitClient.authService.login(request)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true && !loginResponse.token.isNullOrEmpty()) {
                        Log.d("LoginViewModel", "Login exitoso con token: ${loginResponse.token}")
                        loginState.value = true
                        navController.navigate("patientList")
                    } else {
                        errorMessage.value = "Error: Respuesta inválida del servidor"
                        Log.e("LoginViewModel", "Login fallido: Token o success inválido")
                    }


                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.value = "Error: ${response.code()} - $errorBody"
                    Log.e("LoginViewModel", "Error en el Login: $errorBody")
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
                Log.e("LoginViewModel", "Excepción durante el Login: ${e.message}")
            }
        }
    }
}


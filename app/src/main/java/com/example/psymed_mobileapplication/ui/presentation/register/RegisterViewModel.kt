package com.example.psymed_mobileapplication.ui.presentation.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.psymed_mobileapplication.ui.shared.RetrofitClient
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val registrationState = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun registerUser(request: RegisterRequest, navController: NavController? = null) {
        viewModelScope.launch {
            try {
                Log.d("RegisterViewModel", "Iniciando registro con datos: $request")


                if (request.username.isNotEmpty() && request.email.isNotEmpty() && request.password.isNotEmpty()) {
                    Log.d("RegisterViewModel", "Registro de prueba exitoso")
                    registrationState.value = true
                    navController?.navigate("patient_main")
                    return@launch
                }

                val response = RetrofitClient.authService.register(request)
                if (response.isSuccessful) {
                    Log.d("RegisterViewModel", "Registro exitoso")
                    registrationState.value = true
                    navController?.navigate("patient_main")
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.value = "Error: ${response.code()} - $errorBody"
                    Log.e("RegisterViewModel", "Error en el registro: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Excepción durante el registro: ${e.message}")
                if (request.username.isNotEmpty() && request.email.isNotEmpty() && request.password.isNotEmpty()) {
                    Log.d("RegisterViewModel", "Navegando por excepción de red")
                    navController?.navigate("patient_main")
                } else {
                    errorMessage.value = e.message
                }
            }
        }
    }
}

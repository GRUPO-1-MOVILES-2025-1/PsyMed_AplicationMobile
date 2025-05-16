package com.example.psymed_mobileapplication.ui.presentation.register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = RegisterViewModel(),
    navController: NavController
) {
    val registrationState = viewModel.registrationState.value
    val errorMessage = viewModel.errorMessage.value

    if (registrationState) {
        Text("Registro exitoso")
    } else {
        val username = remember { mutableStateOf("") }
        val name = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val birthDate = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val gender = remember { mutableStateOf("") }
        val phone = remember { mutableStateOf("") }
        val ubication = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CreateAccountTitle()
            BackButton(onBackClick = { navController.navigate("login") })
            UsernameField(value = username.value, onValueChange = { username.value = it })
            NameField(value = name.value, onValueChange = { name.value = it })
            LastNameField(value = lastName.value, onValueChange = { lastName.value = it })
            DateOfBirthField(value = birthDate.value, onValueChange = { birthDate.value = it })
            EmailField(value = email.value, onValueChange = { email.value = it })
            GenderField(value = gender.value, onValueChange = { gender.value = it })
            PhoneNumberInput(value = phone.value, onValueChange = { phone.value = it })
            UbicationInput(value = ubication.value, onValueChange = { ubication.value = it })
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") }
            )

            RegisterButton(onClick = {
                val formattedBirthDate = convertDateFormat(birthDate.value)

                Log.d("RegisterScreen", "Datos enviados: $name, $lastName, $birthDate, $email, $gender, $phone, $ubication, $password")
                val request = RegisterRequest(
                    username = username.value,
                    name = name.value,
                    lastName = lastName.value,
                    birthDate = formattedBirthDate,
                    email = email.value,
                    gender = gender.value,
                    phone = phone.value,
                    ubication = ubication.value,
                    password = password.value
                )
                Log.d("RegisterButton", "Datos capturados: $request")
                viewModel.registerUser(request)
                Log.d("RegisterButton", "Datos capturados: $request")
            })


            errorMessage?.let {
                Text("Error: $it", color = Color.Red)
            }
        }
    }
}





fun convertDateFormat(inputDate: String): String {
    return try {
        // Formato de entrada (dd/MM/yyyy)
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        // Formato de salida (yyyy-MM-dd'T'HH:mm:ss.SSS'Z')
        val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        // Parsear la fecha de entrada y formatearla al formato esperado
        val date = inputFormat.parse(inputDate)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        e.printStackTrace()
        "" // Retorna una cadena vacía en caso de error
    }
}
package com.example.psymed_mobileapplication.ui.presentation.patientList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PatientListScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header
        Header(userName = "John Doe")

        // Lista de pacientes (puedes agregar un LazyColumn aquí si es necesario)
        UserProfileCard(
            name = "John Doe",
            age = 30,
            phone = "+51 123456789",
            email = "renatoreyes_8@hotmail.com"
        )

        Spacer(modifier = Modifier.weight(1f)) // Espaciador para empujar los botones al final

        // Footer con botones
        FooterButtons()
    }
}

@Preview(showBackground = true)
@Composable
fun PatientListScreenPreview() {
    PatientListScreen()
}
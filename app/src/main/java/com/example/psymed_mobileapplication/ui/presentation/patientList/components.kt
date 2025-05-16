package com.example.psymed_mobileapplication.ui.presentation.patientList


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    userName: String,
    onSearchClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = "Welcome 'NameUser'", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { /* Acción de navegación */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /* Acción de ajustes */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF10BEAE),
            titleContentColor = Color.White
        )
    )
}

@Preview
@Composable
fun HeaderPreview() {
    Header(userName = "John Doe")
}

@Composable
fun FooterButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { /* Acción para agregar paciente */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10BEAE)
            )) {
            Text(text = "Add Patient")
        }
        Button(onClick = { /* Acción para ver pacientes */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10BEAE)
            )) {
            Text(text = "Watch Patients")
        }
    }
}

@Preview
@Composable
fun FooterButtonsPreview() {
    FooterButtons()
}

@Composable
fun UserProfileCard(
    name: String,
    age: Int,
    phone: String,
    email: String
) {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del usuario
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )

            // Información del usuario
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Edad: $age años")
                Text(text = "Teléfono: $phone")
                Text(text = "Correo: $email")
            }

            // Ícono de correo
            IconButton(onClick = { /* Acción de enviar mensaje */ }) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Enviar mensaje"
                )
            }
        }
    }
}

@Preview
@Composable
fun UserProfileCardPreview() {
    UserProfileCard(
        name = "John Doe",
        age = 30,
        phone = "+51 123456789",
        email = "renatoreyes_8@hotmail.com")
}
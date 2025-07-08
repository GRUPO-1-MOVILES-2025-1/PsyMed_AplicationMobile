package com.example.psymed_mobileapplication.ui.presentation.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Prescription(
    val medicationName: String,
    val dosage: String,
    val frequency: String,
    val duration: String,
    val instructions: String,
    val prescribedBy: String,
    val isActive: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionsScreen(navController: NavController) {
    val prescriptions = listOf(
        Prescription(
            medicationName = "Sertralina",
            dosage = "50mg",
            frequency = "1 vez al día",
            duration = "3 meses",
            instructions = "Tomar en la mañana con alimentos",
            prescribedBy = "Dr. García"
        ),
        Prescription(
            medicationName = "Lorazepam",
            dosage = "0.5mg",
            frequency = "Según necesidad",
            duration = "1 mes",
            instructions = "Máximo 2 veces al día para ansiedad",
            prescribedBy = "Dr. García"
        ),
        Prescription(
            medicationName = "Melatonina",
            dosage = "3mg",
            frequency = "1 vez al día",
            duration = "2 semanas",
            instructions = "Tomar 30 minutos antes de dormir",
            prescribedBy = "Dr. García"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Prescripciones",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF10BEAE)
            )
        )

        // Prescriptions List
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(prescriptions) { prescription ->
                PrescriptionCard(prescription = prescription)
            }
        }
    }
}

@Composable
fun PrescriptionCard(prescription: Prescription) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header with medication name and status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prescription.medicationName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (prescription.isActive)
                                Color(0xFF10BEAE).copy(alpha = 0.1f)
                            else
                                Color(0xFF999999).copy(alpha = 0.1f)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (prescription.isActive) "Activo" else "Inactivo",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (prescription.isActive) Color(0xFF10BEAE) else Color(0xFF999999)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dosage and frequency
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoItem(
                    label = "Dosis",
                    value = prescription.dosage,
                    modifier = Modifier.weight(1f)
                )
                InfoItem(
                    label = "Frecuencia",
                    value = prescription.frequency,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Duration and doctor
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoItem(
                    label = "Duración",
                    value = prescription.duration,
                    modifier = Modifier.weight(1f)
                )
                InfoItem(
                    label = "Prescrito por",
                    value = prescription.prescribedBy,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Instructions
            Column {
                Text(
                    text = "Instrucciones:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF666666)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = prescription.instructions,
                    fontSize = 14.sp,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrescriptionsScreenPreview() {
    PrescriptionsScreen(navController = rememberNavController())
}
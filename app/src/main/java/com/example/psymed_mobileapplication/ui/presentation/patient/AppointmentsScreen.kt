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

data class Appointment(
    val id: Int,
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String,
    val location: String,
    val type: AppointmentType,
    val status: AppointmentStatus
)

enum class AppointmentType {
    CONSULTATION, THERAPY, FOLLOWUP
}

enum class AppointmentStatus {
    UPCOMING, COMPLETED, CANCELLED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsScreen(navController: NavController) {
    val appointments = listOf(
        Appointment(
            id = 1,
            doctorName = "Dr. García",
            specialty = "Psiquiatría",
            date = "15 Enero 2024",
            time = "10:00 AM",
            location = "Consultorio 201",
            type = AppointmentType.CONSULTATION,
            status = AppointmentStatus.UPCOMING
        ),
        Appointment(
            id = 2,
            doctorName = "Dra. Martínez",
            specialty = "Psicología",
            date = "18 Enero 2024",
            time = "3:00 PM",
            location = "Sala de Terapia A",
            type = AppointmentType.THERAPY,
            status = AppointmentStatus.UPCOMING
        ),
        Appointment(
            id = 3,
            doctorName = "Dr. García",
            specialty = "Psiquiatría",
            date = "8 Enero 2024",
            time = "10:00 AM",
            location = "Consultorio 201",
            type = AppointmentType.FOLLOWUP,
            status = AppointmentStatus.COMPLETED
        ),
        Appointment(
            id = 4,
            doctorName = "Dra. López",
            specialty = "Neurología",
            date = "22 Enero 2024",
            time = "11:30 AM",
            location = "Consultorio 305",
            type = AppointmentType.CONSULTATION,
            status = AppointmentStatus.UPCOMING
        )
    )

    val upcomingAppointments = appointments.filter { it.status == AppointmentStatus.UPCOMING }
    val pastAppointments = appointments.filter { it.status != AppointmentStatus.UPCOMING }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Citas",
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
            actions = {
                IconButton(onClick = { /* Add new appointment */ }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Appointment",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF10BEAE)
            )
        )

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Next Appointment Card
            if (upcomingAppointments.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF10BEAE)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = "Próxima Cita",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            val nextAppointment = upcomingAppointments.first()
                            Text(
                                text = nextAppointment.doctorName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = "${nextAppointment.date} • ${nextAppointment.time}",
                                fontSize = 16.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    tint = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = nextAppointment.location,
                                    fontSize = 14.sp,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }
                    }
                }
            }

            // Upcoming Appointments Section
            if (upcomingAppointments.size > 1) {
                item {
                    Text(
                        text = "Próximas Citas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(upcomingAppointments.drop(1)) { appointment ->
                    AppointmentCard(appointment = appointment)
                }
            }

            // Past Appointments Section
            if (pastAppointments.isNotEmpty()) {
                item {
                    Text(
                        text = "Historial de Citas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(pastAppointments) { appointment ->
                    AppointmentCard(appointment = appointment)
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(appointment: Appointment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = appointment.doctorName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Text(
                        text = appointment.specialty,
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )
                }

                // Status Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            when (appointment.status) {
                                AppointmentStatus.UPCOMING -> Color(0xFF10BEAE).copy(alpha = 0.1f)
                                AppointmentStatus.COMPLETED -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                AppointmentStatus.CANCELLED -> Color(0xFFFF5722).copy(alpha = 0.1f)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = when (appointment.status) {
                            AppointmentStatus.UPCOMING -> "Próxima"
                            AppointmentStatus.COMPLETED -> "Completada"
                            AppointmentStatus.CANCELLED -> "Cancelada"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = when (appointment.status) {
                            AppointmentStatus.UPCOMING -> Color(0xFF10BEAE)
                            AppointmentStatus.COMPLETED -> Color(0xFF4CAF50)
                            AppointmentStatus.CANCELLED -> Color(0xFFFF5722)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Date",
                    tint = Color(0xFF666666),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${appointment.date} • ${appointment.time}",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color(0xFF666666),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = appointment.location,
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = when (appointment.type) {
                        AppointmentType.CONSULTATION -> Icons.Default.MedicalServices
                        AppointmentType.THERAPY -> Icons.Default.Psychology
                        AppointmentType.FOLLOWUP -> Icons.Default.Assignment
                    },
                    contentDescription = "Type",
                    tint = Color(0xFF666666),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (appointment.type) {
                        AppointmentType.CONSULTATION -> "Consulta"
                        AppointmentType.THERAPY -> "Terapia"
                        AppointmentType.FOLLOWUP -> "Seguimiento"
                    },
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentsScreenPreview() {
    AppointmentsScreen(navController = rememberNavController())
}

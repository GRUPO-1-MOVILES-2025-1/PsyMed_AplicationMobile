package com.example.psymed_mobileapplication.ui.presentation.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class ProfileOption(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Información", "Configuración", "Seguridad")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Perfil",
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
                IconButton(onClick = { /* Edit profile */ }) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF10BEAE)
            )
        )

        // User Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFF10BEAE).copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color(0xFF10BEAE),
                        modifier = Modifier.size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Juan Pérez García",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "Paciente desde Marzo 2024",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStat("Citas", "12", Color(0xFF10BEAE))
                    ProfileStat("Días activo", "45", Color(0xFF2196F3))
                    ProfileStat("Progreso", "78%", Color(0xFF4CAF50))
                }
            }
        }

        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Color(0xFF10BEAE),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // Tab Content
        when (selectedTab) {
            0 -> PersonalInfoTab()
            1 -> SettingsTab()
            2 -> SecurityTab(navController)
        }
    }
}

@Composable
fun ProfileStat(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF666666)
        )
    }
}

@Composable
fun PersonalInfoTab() {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("Juan Pérez García") }
    var email by remember { mutableStateOf("juan.perez@email.com") }
    var phone by remember { mutableStateOf("+51 987 654 321") }
    var birthDate by remember { mutableStateOf("15/03/1996") }
    var address by remember { mutableStateOf("Av. Arequipa 1234, Lima") }
    var emergencyContact by remember { mutableStateOf("María Pérez - +51 987 123 456") }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Información Personal",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333333)
                        )

                        TextButton(
                            onClick = { isEditing = !isEditing }
                        ) {
                            Text(
                                text = if (isEditing) "Guardar" else "Editar",
                                color = Color(0xFF10BEAE)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileField(
                        label = "Nombre completo",
                        value = name,
                        onValueChange = { name = it },
                        isEditing = isEditing,
                        icon = Icons.Default.Person
                    )

                    ProfileField(
                        label = "Correo electrónico",
                        value = email,
                        onValueChange = { email = it },
                        isEditing = isEditing,
                        icon = Icons.Default.Email
                    )

                    ProfileField(
                        label = "Teléfono",
                        value = phone,
                        onValueChange = { phone = it },
                        isEditing = isEditing,
                        icon = Icons.Default.Phone
                    )

                    ProfileField(
                        label = "Fecha de nacimiento",
                        value = birthDate,
                        onValueChange = { birthDate = it },
                        isEditing = isEditing,
                        icon = Icons.Default.CalendarToday
                    )

                    ProfileField(
                        label = "Dirección",
                        value = address,
                        onValueChange = { address = it },
                        isEditing = isEditing,
                        icon = Icons.Default.LocationOn
                    )

                    ProfileField(
                        label = "Contacto de emergencia",
                        value = emergencyContact,
                        onValueChange = { emergencyContact = it },
                        isEditing = isEditing,
                        icon = Icons.Default.ContactEmergency
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Información Médica",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    MedicalInfoItem("Tipo de sangre", "O+")
                    MedicalInfoItem("Alergias", "Ninguna conocida")
                    MedicalInfoItem("Medicamentos actuales", "Sertralina 50mg")
                    MedicalInfoItem("Médico tratante", "Dr. García - Psiquiatría")
                }
            }
        }
    }
}

@Composable
fun SettingsTab() {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SettingsSection(
                title = "Notificaciones",
                items = listOf(
                    SettingItem("Recordatorios de medicación", true),
                    SettingItem("Citas médicas", true),
                    SettingItem("Tareas diarias", false),
                    SettingItem("Actualizaciones de la app", true)
                )
            )
        }

        item {
            SettingsSection(
                title = "Privacidad",
                items = listOf(
                    SettingItem("Compartir datos anónimos", false),
                    SettingItem("Análisis de uso", true),
                    SettingItem("Sincronización en la nube", true)
                )
            )
        }

        item {
            SettingsSection(
                title = "Aplicación",
                items = listOf(
                    SettingItem("Modo oscuro", false),
                    SettingItem("Sonidos", true),
                    SettingItem("Vibraciones", true)
                )
            )
        }
    }
}

@Composable
fun SecurityTab(navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Seguridad de la cuenta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecurityOption(
                        title = "Cambiar contraseña",
                        subtitle = "Actualiza tu contraseña",
                        icon = Icons.Default.Lock,
                        onClick = { /* Change password */ }
                    )

                    SecurityOption(
                        title = "Autenticación de dos factores",
                        subtitle = "Añade una capa extra de seguridad",
                        icon = Icons.Default.Security,
                        onClick = { /* Setup 2FA */ }
                    )

                    SecurityOption(
                        title = "Dispositivos conectados",
                        subtitle = "Gestiona tus dispositivos",
                        icon = Icons.Default.Devices,
                        onClick = { /* Manage devices */ }
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Datos y privacidad",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecurityOption(
                        title = "Descargar mis datos",
                        subtitle = "Obtén una copia de tu información",
                        icon = Icons.Default.Download,
                        onClick = { /* Download data */ }
                    )

                    SecurityOption(
                        title = "Eliminar cuenta",
                        subtitle = "Elimina permanentemente tu cuenta",
                        icon = Icons.Default.DeleteForever,
                        onClick = { /* Delete account */ },
                        isDestructive = true
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFF5722).copy(alpha = 0.1f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                        tint = Color(0xFFFF5722),
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Cerrar Sesión",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5722)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { navController.navigate("login") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5722)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirmar cierre de sesión")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEditing: Boolean,
    icon: ImageVector
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF666666),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF666666)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (isEditing) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF10BEAE),
                    focusedLabelColor = Color(0xFF10BEAE)
                )
            )
        } else {
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color(0xFF333333),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun MedicalInfoItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color(0xFF333333)
        )
    }
}

@Composable
fun SettingsSection(
    title: String,
    items: List<SettingItem>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                SettingRow(item = item)
            }
        }
    }
}

@Composable
fun SettingRow(item: SettingItem) {
    var isEnabled by remember { mutableStateOf(item.isEnabled) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color(0xFF333333)
        )

        Switch(
            checked = isEnabled,
            onCheckedChange = { isEnabled = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF10BEAE),
                checkedTrackColor = Color(0xFF10BEAE).copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun SecurityOption(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (isDestructive) Color(0xFFFF5722) else Color(0xFF666666),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDestructive) Color(0xFFFF5722) else Color(0xFF333333)
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        }

        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate",
                tint = Color(0xFF999999)
            )
        }
    }
}

data class SettingItem(
    val title: String,
    val isEnabled: Boolean
)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}

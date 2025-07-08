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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val isCompleted: Boolean = false,
    val dueTime: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController) {
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(
                    id = 1,
                    title = "Tomar medicación matutina",
                    description = "Sertralina 50mg con el desayuno",
                    icon = Icons.Default.Medication,
                    color = Color(0xFF10BEAE),
                    dueTime = "08:00 AM"
                ),
                Task(
                    id = 2,
                    title = "Ejercicio de respiración",
                    description = "5 minutos de respiración profunda",
                    icon = Icons.Default.Air,
                    color = Color(0xFF2196F3),
                    dueTime = "10:00 AM"
                ),
                Task(
                    id = 3,
                    title = "Registrar estado de ánimo",
                    description = "Anotar cómo te sientes hoy",
                    icon = Icons.Default.Mood,
                    color = Color(0xFFFF9800),
                    isCompleted = true
                ),
                Task(
                    id = 4,
                    title = "Caminata de 20 minutos",
                    description = "Actividad física ligera",
                    icon = Icons.Default.DirectionsWalk,
                    color = Color(0xFF4CAF50),
                    dueTime = "06:00 PM"
                ),
                Task(
                    id = 5,
                    title = "Técnica de relajación",
                    description = "Relajación muscular progresiva",
                    icon = Icons.Default.SelfImprovement,
                    color = Color(0xFF9C27B0),
                    dueTime = "09:00 PM"
                )
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Lista de Tareas",
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

        // Progress Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                val completedTasks = tasks.count { it.isCompleted }
                val totalTasks = tasks.size
                val progress = if (totalTasks > 0) completedTasks.toFloat() / totalTasks else 0f

                Text(
                    text = "Progreso del día",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$completedTasks de $totalTasks tareas completadas",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF10BEAE),
                    trackColor = Color(0xFFE0E0E0)
                )
            }
        }

        // Tasks List
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onToggleComplete = { taskId ->
                        tasks = tasks.map {
                            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
                            else it
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isCompleted)
                Color(0xFFF5F5F5) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onToggleComplete(task.id) },
                colors = CheckboxDefaults.colors(
                    checkedColor = task.color,
                    uncheckedColor = Color(0xFF999999)
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(task.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = task.icon,
                    contentDescription = task.title,
                    tint = if (task.isCompleted) Color(0xFF999999) else task.color,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Task Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (task.isCompleted) Color(0xFF999999) else Color(0xFF333333),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )

                Text(
                    text = task.description,
                    fontSize = 14.sp,
                    color = if (task.isCompleted) Color(0xFF999999) else Color(0xFF666666),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )

                task.dueTime?.let { time ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "⏰ $time",
                        fontSize = 12.sp,
                        color = if (task.isCompleted) Color(0xFF999999) else task.color
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(navController = rememberNavController())
}

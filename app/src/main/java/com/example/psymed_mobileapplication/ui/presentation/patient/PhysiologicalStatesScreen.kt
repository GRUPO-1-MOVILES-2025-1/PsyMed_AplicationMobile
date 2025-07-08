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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class PhysiologicalState(
    val name: String,
    val icon: ImageVector,
    val color: Color,
    val isActive: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysiologicalStatesScreen(navController: NavController) {
    var selectedStates by remember { mutableStateOf(setOf<String>()) }

    val physiologicalStates = listOf(
        PhysiologicalState("Dolor de cabeza", Icons.Default.Psychology, Color(0xFFE91E63)),
        PhysiologicalState("Fatiga", Icons.Default.Battery1Bar, Color(0xFFFF9800)),
        PhysiologicalState("Tensión muscular", Icons.Default.FitnessCenter, Color(0xFF9C27B0)),
        PhysiologicalState("Problemas digestivos", Icons.Default.Restaurant, Color(0xFF4CAF50)),
        PhysiologicalState("Palpitaciones", Icons.Default.Favorite, Color(0xFFF44336)),
        PhysiologicalState("Sudoración", Icons.Default.WaterDrop, Color(0xFF2196F3)),
        PhysiologicalState("Temblores", Icons.Default.Vibration, Color(0xFF795548)),
        PhysiologicalState("Mareos", Icons.Default.RotateRight, Color(0xFF607D8B))
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
                    text = "Estados Fisiológicos",
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

        // Instructions Card
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
                Text(
                    text = "¿Qué síntomas físicos estás experimentando hoy?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Selecciona todos los que apliquen",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }
        }

        // States Grid
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(physiologicalStates.chunked(2)) { rowStates ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowStates.forEach { state ->
                        PhysiologicalStateCard(
                            state = state,
                            isSelected = selectedStates.contains(state.name),
                            onToggle = {
                                selectedStates = if (selectedStates.contains(state.name)) {
                                    selectedStates - state.name
                                } else {
                                    selectedStates + state.name
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Fill remaining space if odd number of items
                    if (rowStates.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Save selected states
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF10BEAE)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Guardar Registro",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun PhysiologicalStateCard(
    state: PhysiologicalState,
    isSelected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(120.dp),
        onClick = onToggle,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) state.color.copy(alpha = 0.1f) else Color.White
        ),
        border = if (isSelected) CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(state.color)
        ) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = state.icon,
                contentDescription = state.name,
                tint = if (isSelected) state.color else Color(0xFF666666),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.name,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                color = if (isSelected) state.color else Color(0xFF333333),
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhysiologicalStatesScreenPreview() {
    PhysiologicalStatesScreen(navController = rememberNavController())
}

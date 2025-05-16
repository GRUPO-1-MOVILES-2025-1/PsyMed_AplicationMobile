package com.example.psymed_mobileapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.navigation.compose.rememberNavController
import com.example.psymed_mobileapplication.ui.routes.NavGraph
import com.example.psymed_mobileapplication.ui.theme.PsyMed_MobileApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    PsyMed_MobileApplicationTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}
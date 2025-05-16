package com.example.psymed_mobileapplication.ui.routes



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.psymed_mobileapplication.ui.presentation.login.LoginComponents
import com.example.psymed_mobileapplication.ui.presentation.patientList.PatientListScreen
import com.example.psymed_mobileapplication.ui.presentation.register.RegisterScreen

sealed class Route(val route: String) {
    data object Login : Route("login")
    data object Register : Route("register")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.Login.route) {
        composable(Route.Login.route) {
            LoginComponents(
                navController = navController,
                onLoginClick = { /* Acción de login */ },
                onGoogleClick = { /* Acción de Google */ },
                onMicrosoftClick = { /* Acción de Microsoft */ },
                onForgotPasswordClick = { /* Acción de recuperar contraseña */ },
                onSignUpClick = { navController.navigate(Route.Register.route) }
            )
        }
        composable(Route.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable("patientList") {
            PatientListScreen()
        }
    }
}


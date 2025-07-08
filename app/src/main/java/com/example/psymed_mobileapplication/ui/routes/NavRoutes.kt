package com.example.psymed_mobileapplication.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.psymed_mobileapplication.ui.presentation.login.LoginComponents
import com.example.psymed_mobileapplication.ui.presentation.patientList.PatientListScreen
import com.example.psymed_mobileapplication.ui.presentation.register.RegisterScreen
import com.example.psymed_mobileapplication.ui.presentation.patient.*

sealed class Route(val route: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object PatientMain : Route("patient_main")
    data object CurrentTreatment : Route("current_treatment")
    data object Prescriptions : Route("prescriptions")
    data object CurrentDiagnosis : Route("current_diagnosis")
    data object MoodState : Route("mood_state")
    data object PhysiologicalStates : Route("physiological_states")
    data object MoodStatistics : Route("mood_statistics")
    data object TaskList : Route("task_list")
    data object Profile : Route("profile")
    data object Appointments : Route("appointments")
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
        composable(Route.PatientMain.route) {
            PatientMainScreen(navController = navController)
        }
        composable(Route.CurrentTreatment.route) {
            CurrentTreatmentScreen(navController = navController)
        }
        composable(Route.Prescriptions.route) {
            PrescriptionsScreen(navController = navController)
        }
        composable(Route.CurrentDiagnosis.route) {
            CurrentDiagnosisScreen(navController = navController)
        }
        composable(Route.MoodState.route) {
            MoodStateScreen(navController = navController)
        }
        composable(Route.PhysiologicalStates.route) {
            PhysiologicalStatesScreen(navController = navController)
        }
        composable(Route.MoodStatistics.route) {
            MoodStatisticsScreen(navController = navController)
        }
        composable(Route.TaskList.route) {
            TaskListScreen(navController = navController)
        }
        composable(Route.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable("personal_info") {
            // This will be handled by the ProfileScreen tabs
            ProfileScreen(navController = navController)
        }
        composable("settings") {
            // This will be handled by the ProfileScreen tabs
            ProfileScreen(navController = navController)
        }
        composable("security") {
            // This will be handled by the ProfileScreen tabs
            ProfileScreen(navController = navController)
        }
        composable(Route.Appointments.route) {
            AppointmentsScreen(navController = navController)
        }
    }
}

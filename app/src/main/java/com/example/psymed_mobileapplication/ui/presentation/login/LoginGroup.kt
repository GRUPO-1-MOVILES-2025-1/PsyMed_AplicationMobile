package com.example.psymed_mobileapplication.ui.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginComponents(
    navController: NavController,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onMicrosoftClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = LoginViewModel()
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginState = viewModel.loginState


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurvedBottomSquareBox()
        Spacer(modifier = Modifier.height(8.dp))
        UsernameField(value = username.value, onValueChange = { username.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        PasswordFieldWithIcon(value = password.value, onValueChange = { password.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(onClick =
            {
                val request = LoginRequest(
                    username = username.value,
                    email = "", // Si no usas email, puedes dejarlo vacío
                    password = password.value
                )
                viewModel.loginUser(request, navController)


            })
        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginRow(onGoogleClick = onGoogleClick, onMicrosoftClick = onMicrosoftClick)
        Spacer(modifier = Modifier.height(16.dp))
        ForgotPasswordRow(onClick = onForgotPasswordClick)
        Spacer(modifier = Modifier.height(8.dp))
        SignUpRow(onClick = onSignUpClick)
    }


}


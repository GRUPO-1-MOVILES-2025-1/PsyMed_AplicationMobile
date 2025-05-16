package com.example.psymed_mobileapplication.ui.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.psymed_mobileapplication.ui.theme.AppTypography

@Composable
fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Phone, number, email or Username",
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

    )

}



@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Password",
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
        )

}



@Composable
fun IconVisibilityPassword(
    modifier: Modifier = Modifier,
    passwordVisible: Boolean = false,
    onIconClick: () -> Unit = {}

){
    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

    Icon(
        imageVector = icon,
        contentDescription = description,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp))
            .padding(8.dp)
            .clickable { onIconClick() }

    )
}

@Preview(showBackground = true)
@Composable
fun IconVisibilityPasswordPreview() {
    IconVisibilityPassword()
}

@Composable
fun PasswordFieldWithIcon(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Password",
){

    val passwordVisible = remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconVisibilityPassword(
                passwordVisible = passwordVisible.value,
                onIconClick = { passwordVisible.value = !passwordVisible.value }
            )
        }
    )
}


@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(8.dp), // Espaciado general
        shape = RoundedCornerShape(15.dp), // Bordes redondeados como en la imagen
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10BEAE)) // Color de fondo
    ) {
        Text(
            text = "Log in",
            style = AppTypography.bodyLarge,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp) // Relleno interno
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    LoginButton()
}

@Composable
fun SocialLoginRow(onGoogleClick: () -> Unit, onMicrosoftClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("OR", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onGoogleClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCCCCCC))

        ) {
            Text("Continue with Google", color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onMicrosoftClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCCCCCC))
        ) {
            Text("Continue with Microsoft", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SocialLoginRowPreview() {
    SocialLoginRow(
        onGoogleClick = {},
        onMicrosoftClick = {}
    )
}

@Composable
fun ForgotPasswordRow(onClick: () -> Unit) {
    Text(
        "Forgot Password?",
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        style = AppTypography.bodyLarge,
        modifier = Modifier.clickable { onClick() }.padding(top = 8.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordRowPreview() {
    ForgotPasswordRow(onClick = {})
}

@Composable
fun SignUpRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            "Don't have an account?",
            style = AppTypography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.clickable { onClick() }
        )
        Text(
            " Sign Up",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            style = AppTypography.bodyLarge,
            modifier = Modifier.clickable { onClick() }
        )
    }

}

@Composable
fun CurvedBottomSquareBox(
    width: Int = 250, // Ancho en dp
    height: Int = 150, // Alto en dp
    color: Color = Color(0xFF10BEAE), // Color del cuadrado
    cornerRadius: Int = 45 // Radio de curvatura en dp
) {
    Box(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .clip(RoundedCornerShape(bottomStart = cornerRadius.dp, bottomEnd = cornerRadius.dp)) // Curvatura en los vértices inferiores
            .background(color) // Establece el color de fondo
    )
}

@Preview(showBackground = true)
@Composable
fun CurvedBottomSquareBoxPreview() {
    CurvedBottomSquareBox()
}
package com.example.psymed_mobileapplication.ui.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psymed_mobileapplication.ui.theme.AppTypography

@Composable
fun CreateAccountTitle(
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
            .background(color), // Establece el color de fondo
        contentAlignment = androidx.compose.ui.Alignment.Center // Centra el contenido dentro del Box
    ) {
        Text(
            text = "Create account",
            color = Color.White,
            fontSize = 20.sp,
            style = AppTypography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountTitlePreview() {
    CreateAccountTitle()
}

@Composable
fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Username"
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Name"
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}



@Composable
fun LastNameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Last Name"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}



@Composable
fun DateOfBirthField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "mm/dd/yy"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}



@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Email"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}



@Composable
fun GenderField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Gender"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}



@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Phone Number",
    countryCode: String = "+51" // Código de país para Perú
) {
    var phoneNumber by remember { mutableStateOf("") }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Phone Number") },
        leadingIcon = { Text("🇵🇪 +51") }, // Ícono representando el código de país
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun UbicationInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ubication",
    countryCode: String = "+51" // Código de país para Perú
) {
    OutlinedTextField(
        value = value,
        modifier = modifier
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

    )
}



@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(300.dp)
            .height(100.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF10BEAE),
            contentColor = Color.White
        )
    ) {
        Text(text = "Registrarse", fontSize = 18.sp, style = AppTypography.bodyLarge)
    }

}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Button(
        onClick = onBackClick,
        modifier = modifier
            .width(50.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(
            "<",
            fontSize = 24.sp,
            style = AppTypography.bodyLarge,
            color = Color.Black
        ) // Representa el ícono de retroceso
    }
}






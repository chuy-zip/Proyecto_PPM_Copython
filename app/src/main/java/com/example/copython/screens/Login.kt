package com.example.copython.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copython.R
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginLayout()
        }
    }
}

@Composable
fun LoginLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TopClouds(232, 175, 48,50,"")
        CopythonIcon()
        EmailInput("Ingresa tu\n\n información")
        PasswordInput()
        LoginButton("Iniciar Sesión")
        BottomSquare("¿Aún no tienes una cuenta?", 51, 97, 172)
    }
}

@Composable
fun TopClouds(red: Int, green: Int, blue: Int, boxSize: Int,username: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(red, green, blue)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = username,
            fontSize = boxSize.sp)
    }
}

@Composable
fun CopythonIcon() {
    Image(painter = painterResource(id = R.drawable.python),
        contentDescription = "Logo de Python",
        modifier = Modifier.size(150.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(text: String) {

    Text(text = text,
        textAlign = TextAlign.Center,
        color = Color(51,97,172),
        fontSize = 40.sp,
        fontWeight = FontWeight.Black
    )

    var userEmail = ""
    TextField(value = userEmail,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {userEmail = it},
        shape = CircleShape,
        label = {Text("Correo Electrónico") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(22,47,101),
            containerColor = Color(232,199,102),
            unfocusedLabelColor = Color(51,97,172))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput() {
    var userPassword = ""
    TextField(value = userPassword,
        modifier = Modifier.fillMaxWidth(),
        shape = CircleShape,
        onValueChange = {userPassword = it},
        label = {Text("Contraseña") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(22,47,101),
            containerColor = Color(232,175,48),
            unfocusedLabelColor = Color(51,97,172),
            focusedLabelColor =  Color(22,47,101)
        )
    )
}

@Composable
fun BottomSquare(text: String, red: Int, green: Int, blue: Int) {
    Text(text = text,
        color = Color(red, green, blue),
        fontWeight = FontWeight.Black
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(red, green, blue)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "",
            fontSize = 50.sp)
    }
}

@Composable
fun LoginButton(text: String) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        border = BorderStroke(
            width = 5.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Color(232,175,48),
                    Color(51,97,172)
                )
            )
        )
        ) {
        Text(text = text,
            fontSize = 40.sp)
    }
}
@Preview (showBackground = true)
@Composable
fun LoginPreview() {
    COPYTHONTheme {
        LoginLayout()
    }
}
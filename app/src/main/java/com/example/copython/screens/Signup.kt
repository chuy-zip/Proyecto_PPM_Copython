package com.example.copython.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme



@Composable
fun SignupLayout(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TopClouds(51, 97, 172,50,"")
        CopythonIcon()
        EmailInput("Bienvenido")
        PasswordInput()
        ConfirmPasswordInput()
        LoginButton("Registrarse",navController)
        BottomSquare("¿Ya tienes una cuenta? Iniciar sesión.", 232, 175, 48)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordInput() {
    var userPassword = ""
    TextField(value = userPassword,
        modifier = Modifier.fillMaxWidth(),
        shape = CircleShape,
        onValueChange = {userPassword = it},
        label = {Text("Confirma tu contraseña") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(22,47,101),
            containerColor = Color(232,175,48),
            unfocusedLabelColor = Color(51,97,172),
            focusedLabelColor =  Color(22,47,101)
        )
    )
}
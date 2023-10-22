package com.example.copython.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.navigation.AppScreens


@Composable
fun SignupLayout(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TopClouds(51, 97, 172,50,"")
        CopythonIcon()
        val userEmail = emailInput("Bienvenido")
        val userPassword = passwordInput()
        val passwordVerification = confirmPasswordInput()
        SignUpButton("Registrarse", navController, userEmail, userPassword, passwordVerification, LocalContext.current)
        BottomSquare("¿Ya tienes una cuenta? Iniciar sesión.",navController, AppScreens.Login.route, 232, 175, 48)
    }
}

@Composable
fun SignUpButton(text: String, navController: NavController, userEmail: String, userPassword: String, passwordVerification: String, context: Context) {
    OutlinedButton(
        onClick = { signupFun(userEmail, userPassword, passwordVerification, context, navController) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun confirmPasswordInput(): String {
    var userPassword by rememberSaveable { mutableStateOf("") }
    TextField(value = userPassword,
        singleLine = true,
        shape = CircleShape,
        onValueChange = {userPassword = it},
        label = {Text("Confirma tu contraseña") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(22,47,101),
            containerColor = Color(232,175,48),
            unfocusedLabelColor = Color(51,97,172),
            focusedLabelColor =  Color(22,47,101)),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    return userPassword
}

private fun signupFun(email: String, password: String, passwordVerification: String, context: Context, navController: NavController) {

    if (email.isBlank() || password.isBlank()) {
        Toast.makeText(context, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show()
        return
    }

    if (password != passwordVerification) {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        return
    }

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(context, "Te has registrado correctamente", Toast.LENGTH_LONG).show()
            navController.navigate(route = AppScreens.Login.route)

        } else {
            Toast.makeText(context, "No te has podido registrar", Toast.LENGTH_LONG).show()
        }
    }
}
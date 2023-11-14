package com.example.copython.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.copython.googleLogin.SignInState
import com.example.copython.navigation.AppScreens

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick:() -> Unit,
    navController: NavController
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

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

        Button(onClick = onSignInClick) {
            Text(text = "Sign In with google")
        }

        BottomSquare("¿Ya tienes una cuenta? Iniciar sesión.",navController, AppScreens.Login.route, 232, 175, 48)
    }
}
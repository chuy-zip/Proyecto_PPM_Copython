package com.example.copython.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.R
import com.example.copython.googleLoginClient.SignInState
import com.example.copython.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val auth: FirebaseAuth = Firebase.auth
@Composable
fun LoginLayout(state: SignInState,
                onSignInState: () -> Unit,
                navController: NavController) {

    LaunchedEffect(key1 = state.signInError) {
        state.signInError.let { error ->
//            Toast.makeText(
//                context,
//                error,
//                Toast.LENGTH_LONG
//            ).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TopClouds(232, 175, 48,50,"")
        CopythonIcon()
        val emailInput = emailInput("Ingresa tu\n\n información")
        val password = passwordInput()
        LoginButton("Iniciar Sesión", navController, emailInput, password, LocalContext.current, onSignInState)
        BottomSquare("¿Aún no tienes una cuenta?",navController, AppScreens.Signup.route, 51, 97, 172)
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
fun emailInput(text: String): String {

    Text(text = text,
        textAlign = TextAlign.Center,
        color = Color(51,97,172),
        fontSize = 40.sp,
        fontWeight = FontWeight.Black
    )

    var userEmail by rememberSaveable { mutableStateOf("") }
    TextField(value = userEmail, onValueChange = {userEmail = it},
        singleLine = true,
        shape = CircleShape,
        label = {Text("Correo Electrónico") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(22,47,101),
            containerColor = Color(232,199,102),
            unfocusedLabelColor = Color(51,97,172))
    )
    return userEmail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordInput(): String {
    var userPassword by rememberSaveable { mutableStateOf("") }
    TextField(value = userPassword,
        singleLine = true,
        shape = CircleShape,
        onValueChange = {userPassword = it},
        label = {Text("Contraseña") },
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

@Composable
fun BottomSquare(text: String,navController: NavController, route: String, red: Int, green: Int, blue: Int) {
    Text(text = text,
        color = Color(red, green, blue),
        fontWeight = FontWeight.Black,
        modifier = Modifier.clickable(onClick = {navController.navigate(route)})
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
fun LoginButton(
    text: String,
    navController: NavController,
    userEmail: String,
    userPassword: String,
    context: Context,
    onSignInState: () -> Unit
) {
    OutlinedButton(
        onClick = { loginFun(userEmail, userPassword, context, navController) },
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
//
//    Button(
//        modifier = Modifier
//            .padding(10.dp)
//            .fillMaxWidth(),
//        onClick = onSignInState,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xff4285F4)
//        )) {
//        Text(text = "Iniciar sesión con Google")
//    }
}

private fun loginFun(email: String, password: String, context: Context, navController: NavController) {

    if (email.isBlank() || password.isBlank()) {
        Toast.makeText(context, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show()
        return
    }

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(context, "Iniciaste sesión correctamente", Toast.LENGTH_LONG).show()
            navController.navigate(route = AppScreens.MainMenu.route)

        } else {
            Toast.makeText(context, "Correo o contraseña inválidos.", Toast.LENGTH_LONG).show()
        }
    }
}
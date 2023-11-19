package com.example.copython.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.R
import com.example.copython.navigation.AppScreens
import com.example.copython.ui.theme.ui.theme.DarkBlue10
import com.example.copython.ui.theme.ui.theme.LightBlue10
import com.example.copython.ui.theme.ui.theme.Orange10
import com.example.copython.ui.theme.ui.theme.PaleYellow
import com.example.copython.ui.theme.ui.theme.Yellow10
import com.example.copython.ui.theme.ui.theme.LightBlue20

@Composable
fun UserLayout(navController: NavController, innerPadding: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        UserInfo(tittle = "Hector")
        Spacer(modifier = Modifier.height(40.dp))
        OptionButton("10 Lessons Learned",navController,AppScreens.MainMenu.route)
        Spacer(modifier = Modifier.height(20.dp))
        OptionButton("Log out",navController, AppScreens.Login.route)
        // Bottom Navigation Bar
    }
}

@Composable
fun UserInfo(tittle: String){
    Column(
        modifier = Modifier
            .size(420.dp)
            .fillMaxWidth() // Make the Box span the width of the screen
            .background(LightBlue20) // Set background color for the Box
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserPhoto()
        Text(
            text = tittle,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
        )
    }
}

@Composable
fun UserPhoto() {
    Image(painter = painterResource(id = R.drawable.pepe),
        contentDescription = "Logo de Python",
        modifier = Modifier
            .size(250.dp)
            .padding(15.dp)
            .clip(CircleShape)
    )
}

@Composable
fun OptionButton(text: String, navController: NavController, route: String) {
    OutlinedButton(
        modifier = Modifier.width(275.dp),
        onClick = {navController.navigate(route)},
        border = BorderStroke(
            width = 5.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Yellow10,
                    Orange10
                )
            )
        )
    ) {
        Text(text = text,
            fontSize = 30.sp)
    }
}
package com.example.copython.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.copython.R
import com.example.copython.navigation.AppScreens
import kotlin.contracts.contract

@Composable
fun UserLayout(navController: NavController, innerPadding: PaddingValues, onSignOut: () -> Unit){
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        UserInfo()
        Spacer(modifier = Modifier.height(40.dp))
        OptionButton("10 Lessons Learned",navController,AppScreens.MainMenu.route)
        Spacer(modifier = Modifier.height(20.dp))
        LogOutButton(onSignOut)

        // Bottom Navigation Bar
    }
}

@Composable
fun UserInfo(){

    val userName by remember {
        mutableStateOf("Usuario")
    }

    Column(
        modifier = Modifier
            .size(420.dp)
            .fillMaxWidth() // Make the Box span the width of the screen
            .fillMaxHeight()
            .background(Color(51, 97, 172)), // Set background color for the Box
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectImageFromGallery()

        Text(
            text = userName,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .padding(10.dp)
                .height(60.dp)
        )
    }
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
                    Color(232,175,48),
                    Color(255, 87, 34, 255)
                )
            )
        )
    ) {
        Text(text = text,
            fontSize = 30.sp)
    }
}

@Composable
fun LogOutButton(onSignOut: () -> Unit) {
    OutlinedButton(
        modifier = Modifier.width(275.dp),
        onClick = onSignOut,
        border = BorderStroke(
            width = 5.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Color(232,175,48),
                    Color(255, 87, 34, 255)
                )
            )
        )
    ) {
        Text(text = "Sign out",
            fontSize = 30.sp)
    }
}
@Composable
fun SelectImageFromGallery() {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
        
        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri }
        )

    AsyncImage(
        model = selectedImageUri,
        contentDescription = null,
        modifier = Modifier
            .size(250.dp)
            .padding(15.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop)
    
    Button(onClick = { singlePhotoPickerLauncher.launch(
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
    ) }) {
        Text(text = "Cambiar foto")
    }
}
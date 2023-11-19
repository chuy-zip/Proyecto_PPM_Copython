package com.example.copython.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.copython.R
import com.example.copython.ui.theme.ui.theme.Orange10
import com.example.copython.ui.theme.ui.theme.PaleYellow
import com.example.copython.ui.theme.ui.theme.LightBlue20
import com.example.copython.ui.theme.ui.theme.OrangeYellow

@Composable
fun UserLayout(navController: NavController, innerPadding: PaddingValues, onSignOut: () -> Unit){

    var ACTUAL_EMAIL by remember {
        mutableStateOf(auth.currentUser?.email)
    }

    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        UserInfo(ACTUAL_EMAIL)
        Spacer(modifier = Modifier.height(40.dp))
        Spacer(modifier = Modifier.height(20.dp))
        LogOutButton(onSignOut)

        // Bottom Navigation Bar
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfo(ACTUAL_EMAIL: String?){

    var showDialog by remember {
        mutableStateOf(false)
    }

    var userName by remember {
        mutableStateOf("$ACTUAL_EMAIL")
    }

    var userNameToChage by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .size(420.dp)
            .fillMaxWidth() // Make the Box span the width of the screen
            .background(LightBlue20) // Set background color for the Box
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectImageFromGallery()

        Row {
            Text(
                text = userName,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .padding(10.dp)
                    .height(60.dp)
            )

            IconButton(onClick = { showDialog = true }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null)
            }

            if(showDialog) {
                Dialog(onDismissRequest = { showDialog = false },
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(text = "Cambiar nombre de usuario")

                            OutlinedTextField(value = userNameToChage, onValueChange = { userNameToChage = it },
                                label = { Text(text = "Ingresa tu nombre de usuario") },
                                modifier = Modifier.padding(16.dp)
                            )

                            fun changeUserName(): Boolean {
                                userName = userNameToChage
                                return false
                            }

                            Button(
                                onClick = { showDialog = changeUserName() },
                                colors = ButtonDefaults.buttonColors(containerColor = OrangeYellow)) {
                                Text(text = "Aceptar")

                            }
                        }
                    }
                }
            }
        }
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
                    PaleYellow,
                    Orange10
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

    Box {

        Image(painter = painterResource(id = R.drawable.user), contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(15.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        AsyncImage(
            model = selectedImageUri,
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(15.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

    }

    Button(
        onClick = {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    },
        colors = ButtonDefaults.buttonColors(containerColor = OrangeYellow)
    ) {
        Text(text = "Cambiar foto")
    }
}
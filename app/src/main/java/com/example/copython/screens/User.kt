package com.example.copython.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copython.R
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme

class User : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserLayout()
        }
    }
}

@Composable
fun UserLayout(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        UserInfo(tittle = "Hectór")

        OptionButton("10 Lessons Learned")
        OptionButton("User info")
        OptionButton("AI Assistant")
        OptionButton("Log out")
        // Bottom Navigation Bar
        BottomBar()
    }
}

@Composable
fun UserInfo(tittle: String){
    Column(
        modifier = Modifier
            .size(400.dp)
            .fillMaxWidth() // Make the Box span the width of the screen
            .background(Color(51, 97, 172)), // Set background color for the Box
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
fun OptionButton(text: String) {
    OutlinedButton(
        modifier = Modifier.width(275.dp),
        onClick = { /*TODO*/ },
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

@Preview(showBackground = true)
@Composable
fun UserPreview(){
    COPYTHONTheme {
        UserLayout()
    }
}
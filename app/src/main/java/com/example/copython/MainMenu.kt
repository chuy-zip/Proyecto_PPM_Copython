package com.example.copython

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme

class MainMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenuLayout()
        }
    }
}

@Composable
fun mainMenuLayout(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        topTitle("Current Courses")

        arrangeOfCoursesButtons()

        // Bottom Navigation Bar
        bottomBar()
    }

}

@Composable
fun topTitle(tittle: String){
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the Box span the width of the screen
            .background(Color.Blue), // Set background color for the Box
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$tittle",
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
fun arrangeOfCoursesButtons(){
    Column (
        modifier = Modifier
            .height(570.dp)
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        courseButton(courseName = "Basic Concepts of Programming")
        courseButton(courseName = "This is a button for user Course 2")
        courseButton(courseName = "This is a button for user Course 3")

    }

}

@Composable
fun courseButton(courseName: String){

    val newButtonColor = Color(0xFF221387)

    Button(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = newButtonColor,
            contentColor = Color.White)) {
        Text(
            fontSize = 20.sp,
            text = courseName,
            textAlign = TextAlign.Start)
    }

}

@Composable
fun bottomBar(){
    Row( modifier = Modifier
        .fillMaxWidth()
        .height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround){

        Button(
            onClick = {},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White)) {
            Text(text = "Main")
        }
        Button(
            onClick = {},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White)) {
            Text(text = "Search")
        }
        Button(
            onClick = {},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White)) {
            Text(text = "AI")
        }
        Button(
            onClick = {},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White)) {
            Text(text = "USER")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    COPYTHONTheme {
        mainMenuLayout()
    }
}
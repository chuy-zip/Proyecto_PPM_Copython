package com.example.copython.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme


@Composable
fun SearchCoursesActivityLayout(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TopTitle("Available Courses")

        ScrollableCoursesColumn()

        // Bottom Navigation Bar
        BottomBar()
    }

}

@Composable
fun ScrollableCoursesColumn(){
    Column (
        modifier = Modifier
            .padding(30.dp)
            .height(570.dp)
            .verticalScroll(rememberScrollState())
    ){
        CourseButton("Introduction to Programming Concepts")
        CourseButton("Variables and Data Types")
        CourseButton("Conditional Statements (if-else)")
        CourseButton("Loops (for and while)")
        CourseButton("Functions and Modular Programming")
        CourseButton("Lists and Arrays")
        CourseButton("Working with Strings")
        CourseButton("Introduction to Object-Oriented Programming")
    }
}
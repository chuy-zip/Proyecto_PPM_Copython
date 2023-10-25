package com.example.copython.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun SearchCoursesActivityLayout(navController: NavController, innerPadding: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        ScrollableCoursesColumn(navController)

    }

}

@Composable
fun ScrollableCoursesColumn(navController: NavController){
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        CourseButton("Introduction to Programming Concepts",navController, "3")
        CourseButton("Variables, Data Types and how to show them",navController, "4")
        CourseButton("Conditional Statements, if and else examples",navController, "5")
        CourseButton("Loops (for and while), learn basics of iteration",navController, "6")
        CourseButton("Functions, methods and Modular Programming",navController, "7")
        CourseButton("Lists and Arrays, introduction to data structures",navController, "8")
        CourseButton("Defensive programming and how to debug programs",navController, "9")
        CourseButton("Introduction to Object-Oriented Programming",navController, "10")
    }
}
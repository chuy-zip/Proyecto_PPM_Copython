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
        CourseButton("Variables y como usarlas",navController, "0")
        CourseButton("Declaraciones condicionales, if-else",navController, "1")
        CourseButton("Ciclos (for and while), bases de iteración",navController, "3")
        CourseButton("Funciones y sus usos",navController, "4")
        CourseButton("Listas(Arrays) y tuplas",navController, "2")
        CourseButton("Clases en Python",navController, "5")
    }
}
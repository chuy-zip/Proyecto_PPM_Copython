package com.example.copython

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
import com.example.copython.ui.theme.ui.theme.COPYTHONTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchCoursesActivityLayout()
        }
    }
}

@Composable
fun SearchCoursesActivityLayout(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        topTitle("Available Courses")

        scrollableCoursesColumn()

        // Bottom Navigation Bar
        bottomBar()
    }

}


@Composable
fun scrollableCoursesColumn(){
    Column (
        modifier = Modifier
            .padding(30.dp)
            .height(570.dp)
            .verticalScroll(rememberScrollState())
    ){
        courseButton("Introduction to Programming Concepts")
        courseButton("Variables and Data Types")
        courseButton("Conditional Statements (if-else)")
        courseButton("Loops (for and while)")
        courseButton("Functions and Modular Programming")
        courseButton("Lists and Arrays")
        courseButton("Working with Strings")
        courseButton("Introduction to Object-Oriented Programming")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    COPYTHONTheme {
        SearchCoursesActivityLayout()
    }

}
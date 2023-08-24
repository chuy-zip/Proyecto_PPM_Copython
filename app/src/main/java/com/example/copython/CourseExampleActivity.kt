package com.example.copython

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class CourseExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun CourseExampleLayout(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        topTitle("Introduction to Python")

        scrollableTextsColumn()

        // Bottom Navigation Bar
        bottomBar()
    }

}

@Composable
fun scrollableTextsColumn(){
    Column (
        modifier = Modifier
            .padding(30.dp)
            .height(570.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            text = """
                Python is a popular programming language known for its simplicity and readability. It is often used for web development, data analysis, artificial intelligence, and more.

                Here's the classic "Hello, World!" program in Python:
                
                """.trimIndent(),
            fontSize = 16.sp,
            color = Color.Black
        )

        Box( modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
        ){
            Text(
                text = "\n  printf('Hello World')"
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 25.dp)) // Add padding below the Box


        Box( modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
        ){
            Text(
                text = " \n Output: \n\n Hello World"
            )
        }

        Text(
            text = """
                
                This program prints the text "Hello, World!" to the console. It's often the first program beginners write when learning a new programming language.
            """.trimIndent(),
            fontSize = 16.sp,
            color = Color.Black
        )


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CourseExampleLayout()
}
package com.example.copython.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.ui.theme.ui.theme.Blue10


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseExampleLayout(navController: NavController){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Blue10,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "Hello World",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
            )
        }

    ){ innerPadding ->
        ScrollableTextsColumn(innerPadding)
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        ScrollableTextsColumn()
//    }

}

@Composable
fun ScrollableTextsColumn(innerPadding: PaddingValues) {
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
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

        Box( modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
        ){
            Text(
                text = " Caja de prueba para el scroll"
            )
        }
    }
}

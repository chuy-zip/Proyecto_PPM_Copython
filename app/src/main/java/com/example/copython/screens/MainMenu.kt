package com.example.copython.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.Classes.BottomBarItem
import com.example.copython.navigation.AppScreens
import com.example.copython.ui.theme.ui.theme.Blue10
import com.example.copython.ui.theme.ui.theme.OrangeYellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuLayout(navController: NavController){

    var currentScreenName by remember { mutableStateOf( "Cursos") }
    var currentScreenTitle by remember { mutableStateOf( "Cursos actuales") }

    when (currentScreenName) {
        "Cursos" -> {
           currentScreenTitle = "Cursos Actuales"
        }
        "Buscar" -> {
            currentScreenTitle = "Cursos disponibles"
        }
        "Asistente" -> {
            currentScreenTitle = "Asistente IA"
        }
        "Perfil" -> {
            currentScreenTitle = "Configuraciones y perfil"
        }
        else -> {
            // Code to execute when none of the cases match
        }
    }

    val screens = listOf(
        BottomBarItem(
            title = "Cursos",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomBarItem(
            title = "Buscar",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomBarItem(
            title = "Asistente",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face
        ),BottomBarItem(
            title = "Perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Blue10,
                    titleContentColor = Color.White
                ),
                title = {

                    Text( currentScreenTitle )
                }

            )
        },
        bottomBar = {
            BottomNavigation (
                backgroundColor = Blue10,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                screens.forEach { screen ->
                    BottomNavigationItem(
                        label = { Text(text = screen.title, style = TextStyle( color = Color.White)) },
                        selected = false,
                        onClick = { currentScreenName = screen.title },
                        alwaysShowLabel = true,
                        selectedContentColor = OrangeYellow,
                        unselectedContentColor = Color.White,
                        icon = {
                            Icon(
                                imageVector = screen.selectedIcon,
                                contentDescription = screen.title,
                                tint = Color.White) },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),

                    )
                }
            }
        },
        content = {innePadding ->
            when (currentScreenName) {
                "Cursos" -> {
                    ArrangeOfCoursesButtons(navController =  navController, innePadding)
                }
                "Buscar" -> {
                    SearchCoursesActivityLayout(navController = navController)
                }
                "Asistente" -> {
                    AIChatLayout(navController = navController)
                }
                "Perfil" -> {
                    UserLayout(navController = navController)
                }
                else -> {
                    // Code to execute when none of the cases match
                }
            }

        }
    )

//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        ArrangeOfCoursesButtons(navController)
//    }

}

@Composable
fun TopTitle(tittle: String){
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the Box span the width of the screen
            .background(Color(51, 97, 172)), // Set background color for the Box
        contentAlignment = Alignment.Center
    ) {
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
fun ArrangeOfCoursesButtons(navController: NavController, innePadding: PaddingValues){
    Column (
        modifier = Modifier
            .padding(innePadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        CourseButton(courseName = "Basic Concepts of Programming", navController )
        CourseButton(courseName = "This is a button for user Course 2", navController)
        CourseButton(courseName = "This is a button for user Course 3", navController)

    }

}

@Composable
fun CourseButton(courseName: String, navController: NavController){

    val newButtonColor = Color(0xFF221387)

    Button(
        onClick = {navController.navigate(AppScreens.CourseExampleActivity.route)},
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
fun BottomBar(navController: NavController){
    Row( modifier = Modifier
        .fillMaxWidth()
        .height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround){

        Button(
            onClick = {navController.navigate(AppScreens.MainMenu.route)},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(51,97,172),
                contentColor = Color.White)) {
            Text(text = "Main")
        }
        Button(
            onClick = {navController.navigate(AppScreens.SearchActivity.route)},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(51,97,172),
                contentColor = Color.White)) {
            Text(text = "Search")
        }
        Button(
            onClick = {navController.navigate(AppScreens.AIChatActivity.route)},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(51,97,172),
                contentColor = Color.White)) {
            Text(text = "AI")
        }
        Button(
            onClick = {navController.navigate(AppScreens.User.route)},
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(51,97,172),
                contentColor = Color.White)) {
            Text(text = "USER")
        }

    }
}
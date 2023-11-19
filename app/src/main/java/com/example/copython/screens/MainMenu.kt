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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.copython.ui.theme.ui.theme.DarkBlue10
import com.example.copython.ui.theme.ui.theme.LightBlue10
import com.example.copython.ui.theme.ui.theme.OrangeYellow
import com.example.copython.ui.theme.ui.theme.PaleYellow
import com.example.copython.ui.theme.ui.theme.Yellow10
import com.example.copython.ui.theme.ui.theme.LightBlue20
import kotlinx.coroutines.tasks.await

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuLayout(navController: NavController, onSignOut: () -> Unit){

    var currentScreenName by remember { mutableStateOf( "Buscar") }
    var currentScreenTitle by remember { mutableStateOf( "Cursos Disponibles") }

    when (currentScreenName) {
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
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
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
                modifier = Modifier.fillMaxWidth()
            ){
                screens.forEachIndexed { index, screen ->
                    BottomNavigationItem(
                        label = {
                            Text(
                                text = screen.title,
                                style = TextStyle( if(index == selectedItemIndex) OrangeYellow else Color.White )
                            )},
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            currentScreenName = screen.title },
                        alwaysShowLabel = true,
                        selectedContentColor = OrangeYellow,
                        unselectedContentColor = Color.White,
                        icon = {
                            Icon(
                                imageVector =  if(index == selectedItemIndex){
                                    screen.selectedIcon
                                } else screen.unselectedIcon,
                                contentDescription = screen.title,
                                tint = if(index == selectedItemIndex) OrangeYellow else Color.White,
                            )},
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                    )
                }
            }
        },
        content = {innerPadding ->
            when (currentScreenName) {
                "Cursos" -> {
                    ArrangeOfCoursesButtons(navController =  navController, innerPadding)
                }
                "Buscar" -> {
                    SearchCoursesActivityLayout(navController = navController, innerPadding)
                }
                "Asistente" -> {
                    AIChatLayout(navController = navController, innerPadding)
                }
                "Perfil" -> {
                    UserLayout(navController = navController, innerPadding, onSignOut)
                }
                else -> {
                    // Code to execute when none of the cases match
                }
            }

        }
    )

}

@Composable
fun ArrangeOfCoursesButtons(navController: NavController, innePadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innePadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userCourses by remember { mutableStateOf<List<Boolean>>(emptyList()) }

        LaunchedEffect(key1 = ACTUAL_EMAIL) {

        }
        LazyColumn {
            items(userCourses.size) { index ->
                if (userCourses[index]) {
                    when (index) {
                        0 -> CourseButton(courseName = "Variables y como usarlas", navController, "0")
                        1 -> CourseButton(courseName = "Declaraciones condicionales, if-else", navController, "1")
                        2 -> CourseButton(courseName = "Listas(Arrays) y tuplas", navController, "2")
                        3 -> CourseButton(courseName = "Ciclos (for and while), bases de iteración", navController, "3")
                        4 -> CourseButton(courseName = "Funciones y sus usos", navController, "4")
                        5 -> CourseButton(courseName = "Clases en python", navController, "5")
                    }
                }
            }
        }
    }
}


@Composable
fun CourseButton(courseName: String, navController: NavController, courseToken:String){

    Button(
        onClick = {navController.navigate(route = AppScreens.CourseExampleActivity.route + "/$courseToken")},
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkBlue10,
            contentColor = Color.White)) {
        Text(
            fontSize = 20.sp,
            text = courseName,
            textAlign = TextAlign.Left,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}

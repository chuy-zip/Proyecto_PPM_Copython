package com.example.copython.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copython.screens.AIChatLayout
import com.example.copython.screens.CourseExampleLayout
import com.example.copython.screens.Login
import com.example.copython.screens.LoginLayout
import com.example.copython.screens.MainMenuLayout
import com.example.copython.screens.SearchCoursesActivityLayout
import com.example.copython.screens.Signup
import com.example.copython.screens.SignupLayout
import com.example.copython.screens.UserLayout

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Login.route) {

        composable(route = AppScreens.Login.route) {
            LoginLayout(navController)
        }

        composable(route = AppScreens.Signup.route) {
            SignupLayout(navController)
        }

        composable(route = AppScreens.MainMenu.route) {
            MainMenuLayout(navController)
        }

        composable(route = AppScreens.SearchAtivity.route) {
            SearchCoursesActivityLayout(navController)
        }

        composable(route = AppScreens.AIChatActivity.route) {
            AIChatLayout(navController)
        }

        composable(route = AppScreens.CourseExampleActivity.route) {
            CourseExampleLayout(navController)
        }

        composable(route = AppScreens.User.route) {
            UserLayout(navController)
        }
    }
}
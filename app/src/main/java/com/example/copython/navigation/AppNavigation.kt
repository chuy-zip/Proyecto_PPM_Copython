package com.example.copython.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.copython.screens.CourseExampleLayout
import com.example.copython.screens.SignupLayout

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Login.route) {
        composable(route = AppScreens.MainMenu.route) {
            //MainMenuLayout(navController)
        }

        composable(route = AppScreens.CourseExampleActivity.route + "/{courseToken}",
            arguments = listOf(
                    navArgument(name = "courseToken"){type = NavType.StringType}
                    )
        ) {
            CourseExampleLayout(navController, it.arguments?.getString("courseToken"))
        }

    }
}
package com.example.copython.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copython.screens.CourseExampleLayout
import com.example.copython.screens.LoginLayout
import com.example.copython.screens.MainMenuLayout
import com.example.copython.screens.SignupLayout

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Login.route) {

        composable(route = AppScreens.Login.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                ) },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }) {
            LoginLayout(navController)
        }

        composable(route = AppScreens.Signup.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                ) },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }) {
            SignupLayout(navController)
        }

        composable(route = AppScreens.MainMenu.route) {
            MainMenuLayout(navController)
        }

        composable(route = AppScreens.CourseExampleActivity.route) {
            CourseExampleLayout(navController)
        }

    }
}
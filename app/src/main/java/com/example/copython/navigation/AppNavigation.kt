package com.example.copython.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.copython.googleLogin.GoogleAuthUiClient
import com.example.copython.googleLogin.SignInViewModel
import com.example.copython.screens.CourseExampleLayout
import com.example.copython.screens.LoginLayout
import com.example.copython.screens.MainMenuLayout
import com.example.copython.screens.SignInScreen
import com.example.copython.screens.SignupLayout
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(googleAuthUiClient: GoogleAuthUiClient){
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

        composable(route = AppScreens.SignInScreen.route,
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

        composable(route = AppScreens.CourseExampleActivity.route + "/{courseToken}",
            arguments = listOf(
                    navArgument(name = "courseToken"){type = NavType.StringType}
                    )
        ) {
            CourseExampleLayout(navController, it.arguments?.getString("courseToken"))
        }
    }
}
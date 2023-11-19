package com.example.copython.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import com.example.copython.googleLoginClient.GoogleLoginClient
import com.example.copython.googleLoginClient.ProfileScreen
import com.example.copython.googleLoginClient.SignInViewModel
import com.example.copython.navigation.AppScreens
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleLoginClient by lazy {
        GoogleLoginClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = AppScreens.Login.route) {
                composable(AppScreens.Login.route,
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
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(key1 = Unit) {
                        if(googleLoginClient.getSignInedUser() != null) {
                            navController.navigate(AppScreens.MainMenu.route)
                        }
                    }
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if(result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleLoginClient.signInWithIntent(
                                        intent = result.data?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccesful) {
                        if(state.isSignInSuccesful) {
                            Toast.makeText(
                                applicationContext,
                                "Se pudo",
                                Toast.LENGTH_SHORT
                            ).show()

                            navController.navigate(AppScreens.MainMenu.route)
                            viewModel.resetState()
                        }
                    }

                    LoginLayout(
                        onSignInState = {
                            lifecycleScope.launch {
                                val signInIntendSender = googleLoginClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntendSender?: return@launch
                                    ).build()
                                )
                            }
                        },
                        navController
                    )
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

                composable(route = AppScreens.MainMenu.route,
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
                    MainMenuLayout(navController,
                        onSignOut = {
                            lifecycleScope.launch {
                                googleLoginClient.signOut()
                                auth.signOut()
                                Toast.makeText(
                                    applicationContext,
                                    "Hasta otra",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.popBackStack()
                            }
                        })
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
    }
}
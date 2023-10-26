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
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copython.Google.GoogleAuthUiClient
import com.example.copython.Google.ProfileScreen
import com.example.copython.Google.SignInViewModel
import com.example.copython.navigation.AppNavigation
import com.example.copython.navigation.AppScreens
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy{
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = AppScreens.SignInScreen.route){
                composable(AppScreens.SignInScreen.route){
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(key1 = Unit){
                        if(googleAuthUiClient.getSignedInUser() != null){
                            navController.navigate("profile")
                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract =  ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = {result ->
                            if(result.resultCode == RESULT_OK){
                                lifecycleScope.launch{
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )
                    
                    LaunchedEffect(key1 = state.isSignInSuccessful){
                        if(state.isSignInSuccessful){
                            Toast.makeText(
                                applicationContext,
                                "Sign in successful",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.navigate("profile")
                            viewModel.resetState()
                        }
                    }

                    SignInScreen(
                        state = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        },
                        navController
                    )
                }
                composable(route = "profile"){
                    ProfileScreen(
                        userData = googleAuthUiClient.getSignedInUser(),
                        onSignOut = {
                            lifecycleScope.launch{
                                googleAuthUiClient.signOut()
                                Toast.makeText(
                                    applicationContext,
                                    "Signed out",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.popBackStack()
                            }
                        }
                    )
                }
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

                composable(route = AppScreens.MainMenu.route) {
                    MainMenuLayout(navController)
                }

                composable(route = AppScreens.CourseExampleActivity.route) {
                    CourseExampleLayout(navController)
                }
            }
        }
    }
}

@Composable
fun MenuTest() {
    Column {
        TextTest(text = "Funciona y tal.")
    }
}

@Composable
fun TextTest(text: String) {
    Text(text)
}

@Preview
@Composable
fun PreviewText() {
    MenuTest()
}
package com.example.copython.navigation

sealed class AppScreens (val route: String){
    object AIChatActivity: AppScreens("ai_screen")
    object CourseExampleActivity: AppScreens("course_screen")
    object Login: AppScreens("login_screen")
    object MainMenu: AppScreens("mainMenu_screen")
    object SearchActivity: AppScreens("search_screen")
    object SignInScreen: AppScreens("signup_screen")
    object User: AppScreens("user_screen")
    object GoogleSignIn: AppScreens("google")
}

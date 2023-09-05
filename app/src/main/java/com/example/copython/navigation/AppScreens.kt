package com.example.copython.navigation

sealed class AppScreens (val route: String){
    object AIChatActivity: AppScreens("ai_screen")
    object CourseExampleActivity: AppScreens("course_screen")
    object MainMenu: AppScreens("mainMenu_screen")

}

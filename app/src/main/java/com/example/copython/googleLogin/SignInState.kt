package com.example.copython.googleLogin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
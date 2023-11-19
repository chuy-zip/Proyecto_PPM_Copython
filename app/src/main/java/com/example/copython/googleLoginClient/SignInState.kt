package com.example.copython.googleLoginClient

data class SignInState(
    val isSignInSuccesful: Boolean = false,
    val signInError: String? = null
)
package com.marsoftwar.muslimamigo.viewmodels

//A default value for the variable is eligible
data class AuthUiState(
    val nEmail: String="",
    val nPassword: String="",
    val email: String="",
    val password: String="",
    val verificationCode:String="",
    val isSignIn: Boolean=false,
    val isError:Boolean=false,
    val isLoading:Boolean=false
)

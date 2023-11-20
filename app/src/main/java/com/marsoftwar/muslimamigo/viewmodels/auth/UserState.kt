package com.marsoftwar.muslimamigo.viewmodels.auth

data class UserState(
    val isSignIn:Boolean?=false,
    val userName:String?="",
    val userEmail:String?=""
)

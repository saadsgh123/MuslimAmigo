package com.marsoftwar.muslimamigo.data.local.preference

data class UserPreference(
    val isSignIn:Boolean?=false,
    val userName:String? = "",
    val userEmail:String? = ""
)

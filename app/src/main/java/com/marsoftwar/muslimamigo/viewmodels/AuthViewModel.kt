package com.marsoftwar.muslimamigo.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.marsoftwar.muslimamigo.authentication.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    private var _authState = MutableStateFlow(AuthUiState())
    val authState:StateFlow<AuthUiState> = _authState.asStateFlow()

    private var _error = MutableStateFlow("")
    val error:StateFlow<String> = _error.asStateFlow()

    var currentUser = mutableStateOf("")
        private set


    suspend fun createAnAccount(isTaskDone: (Boolean) -> Unit) {
        withContext(Dispatchers.IO){
            if (validFormatForSignUp()){
                _authState.update { it.copy(isLoading = true) }
                auth.createUserWithEmailAndPassword(_authState.value.nEmail,_authState.value.nPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            isTaskDone(task.isSuccessful)
                            _authState.update { it.copy(isLoading = false) }
                        }else {
                            _authState.update { it.copy(isError = true) }
                            _error.value = task.exception?.message.toString()
                            isTaskDone(task.isSuccessful)
                            _authState.update { it.copy(isLoading = false) }
                        }
                    }
            } else {
                _authState.update { it.copy(isError = true) }
                isTaskDone(false)
                _authState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun validFormatForSignUp() : Boolean {
        return _authState.value.nEmail.endsWith("@gmail.com") && _authState.value.nPassword.length > 7
    }
    private fun validFormatForSignIn() : Boolean {
        return _authState.value.email.endsWith("@gmail.com") && _authState.value.password.length > 7
    }

    private fun checkIfSignIn():Boolean {
        return false
    }

    fun updateNPassword(newPassword:String) {
        _authState.update { it.copy(nPassword = newPassword) }
    }

    fun updateNEmail(newEmail:String) {
        _authState.update { it.copy(nEmail = newEmail) }
    }

    //SignIn logic
    fun updatePassword(newPassword:String) {
        _authState.update { it.copy(password = newPassword) }
    }

    fun updateEmail(newEmail:String) {
        _authState.update { it.copy(email = newEmail) }
    }

    fun error_handler(error:String) : Boolean{
        _error.value = error
        _authState.update { it.copy(isLoading = false, isError = true) }
        return false
    }

    suspend fun signInExistingAccount(isTaskDone:(Boolean)->Unit) {
        if (validFormatForSignIn()){
            withContext(Dispatchers.IO){
                _authState.update { it.copy(isLoading = true) }
                if (!_authState.value.isSignIn){
                    auth.signInWithEmailAndPassword(_authState.value.email,_authState.value.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                val user = auth.currentUser
                                currentUser.value = user?.email.toString()
                                _error.value = task.exception?.message.toString()
                                isTaskDone(true)
                                _authState.update { it.copy(isLoading = false, isSignIn = true) }
                            } else {
                                isTaskDone(error_handler(task.exception?.message.toString()))
                            }
                        }
                } else {
                    isTaskDone(error_handler("you are already Log in"))
                }
            }
        }else {
            _error.value = "Invalid Format"
        }
    }

    fun onSignInResult(result: SignInResult,isDone: (Boolean) -> Unit) {
        _authState.update { it.copy(
            isSignIn = result.data != null,
            isError = result.errorMessage?.isNotEmpty() == true,
        ) }
        _error.value = result.errorMessage.toString()
        isDone(result.data != null)
    }


}







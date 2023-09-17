package com.marsoftwar.muslimamigo.authentication


import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FacebookAuthClient(private val activity: Activity) {

    private val callbackManager = CallbackManager.Factory.create()
    private val auth = FirebaseAuth.getInstance()
    private val button = LoginButton


    fun initialize() {
        // Initialize Facebook Login
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // Handle Facebook login success
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }
            })
    }

    fun signIn() {
        // Start Facebook login process
        LoginManager.getInstance().logInWithReadPermissions(activity, listOf("email"))
    }

    suspend fun handleFacebookSignInResult(intent: Intent): AuthResult {
        return suspendCancellableCoroutine { continuation ->
            callbackManager.onActivityResult(0, Activity.RESULT_OK, intent)

            // The Facebook callback will handle the result and call the appropriate callbacks
            // In this example, we assume that handleFacebookLoginSuccess is called on success

            // Define your AuthResult class with the necessary data and error fields
            // You should handle error cases appropriately

            // In handleFacebookLoginSuccess, you can authenticate with Firebase using the Facebook access token
            // and return the result in AuthResult
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.

                }
            }
    }

    fun signOut() {
        // Sign out from Facebook
        LoginManager.getInstance().logOut()

        // Sign out from Firebase
        auth.signOut()
    }
}

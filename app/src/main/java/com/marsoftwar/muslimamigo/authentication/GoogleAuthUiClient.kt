package com.marsoftwar.muslimamigo.authentication

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.signin.internal.SignInClientImpl
import com.marsoftwar.muslimamigo.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val oneTapClient: SignInClient,
    private val context:Context
) {
    suspend fun signInWithIntent(intent: Intent) {

    }
    suspend fun SignIn() : IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e : Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }
    private fun buildSignInRequest() : BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions.Builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.Google_auth_id))
            .build()).setAutoSelectEnabled(true)
            .build()
    }

}
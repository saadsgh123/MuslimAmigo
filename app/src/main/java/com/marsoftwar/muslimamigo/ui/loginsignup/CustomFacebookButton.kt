package com.marsoftwar.muslimamigo.ui.loginsignup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.marsoftwar.muslimamigo.R

@Composable
fun CustomFacebookButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onSuccess: (LoginResult) -> Unit,
    onCancel: () -> Unit,
    onError: (FacebookException?) -> Unit,
) {
    val callbackManager = FacebookUtil.callbackManager
    val loginText = stringResource(R.string.txt_connect_with_facebook)

    AndroidView(
        modifier = modifier,
        factory = ::LoginButton,
        update = { button ->  
            button.setPermissions("email")
            button.loginText = loginText
            button.isEnabled = enabled

            button.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
                override fun onCancel() {
                    onCancel()
                }

                override fun onError(error: FacebookException) {
                    onError(error)
                }

                override fun onSuccess(result: LoginResult) {
                    onSuccess(result)
                }
            })
        }
    )

}

object FacebookUtil {
    val callbackManager by lazy {
        CallbackManager.Factory.create()
    }
}
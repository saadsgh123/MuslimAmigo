package com.marsoftwar.muslimamigo.ui.loginsignup

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.viewmodels.auth.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInSignInScreen(
    modifier: Modifier = Modifier,
    googleAuthUiClient: GoogleAuthUiClient,
    content: @Composable() ((AuthViewModel) -> Unit)?
) {

    val config = LocalConfiguration.current
    val height = config.screenHeightDp
    val scope = rememberCoroutineScope()

    val authViewModel = hiltViewModel<AuthViewModel>()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                scope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    authViewModel.onSignInResult(signInResult){ done->

                    }
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit){

    }



    BottomSheetScaffold(
        sheetContent = {
         content?.invoke(authViewModel)
        },
    content = {
        ScreenImage(modifier = modifier)
    },
    sheetContentColor = Color.White,
        sheetPeekHeight = height.dp * 0.7f
    )
}

@Composable
fun ScreenImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logimage),
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterPrev() {}
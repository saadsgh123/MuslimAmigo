package com.marsoftwar.muslimamigo.ui.loginsignup

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.ui.theme.DarkCyan
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SheetContentLogInScreen(
    viewModel: AuthViewModel,
    googleAuthUiClient: GoogleAuthUiClient,
    navigateToMainScreen:()->Unit
) {

    val state by viewModel.authState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isDone = remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val error = viewModel.error.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult){ done ->
                        isDone.value = done
                        showDialog=true
                    }
                }
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (showDialog){
            CustomDialog(
                error = error.value,
                isDone = isDone.value,
                onDone = {
                    if (isDone.value){
                        navigateToMainScreen()
                    }else {
                        showDialog=false
                    }

                },
                isLoading = state.isLoading,
                onDismissRequest = { showDialog=false }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(placeholder = "Email", text = state.email){
                viewModel.updateEmail(it)
            }
            CustomTextField(placeholder = "Password", text = state.password){
                viewModel.updatePassword(it)
            }
            ElevatedButton(
                onClick = {
                    scope.launch {
                        viewModel.signInExistingAccount {
                            isDone.value = it
                            if (viewModel.error.value != ""){
                                Toast.makeText(context,viewModel.error.value,Toast.LENGTH_SHORT).show()
                            }
                        }
                        showDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = DarkCyan),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Log In")
            }
            Row {
                CustomIconButton(image = R.drawable.google){
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
                CustomIconButton(image = R.drawable.facebook_bottom){
                    scope.launch {
                        googleAuthUiClient.signOut()
                    }
                }
            }
        }
    }
}


@Composable
private fun CustomDialog(
    error:String,
    isDone:Boolean,
    onDone: () -> Unit,
    isLoading:Boolean,
    onDismissRequest: () -> Unit
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        onDismissRequest = onDismissRequest
    ) {
        DialogContent(isDone = isDone, isLoading = isLoading, error =error ) {
            onDone()
        }
    }

}

@Composable
 fun DialogContent(error:String,isDone: Boolean,isLoading: Boolean,onDone: () -> Unit) {
        Column(
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Box {
                    CircularProgressIndicator()
                }
            }else {
                Text(
                    text =if(isDone) "Welcome again" else error,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Image(
                    painter = if (isDone ) painterResource(id = R.drawable.accept) else painterResource(
                        id = R.drawable.cross
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDone,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Continue")
                }
            }
        }
}

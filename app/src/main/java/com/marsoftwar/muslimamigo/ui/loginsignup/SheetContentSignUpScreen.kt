package com.marsoftwar.muslimamigo.ui.loginsignup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.auth.FirebaseAuth
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.ui.theme.DarkCyan
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel
import kotlinx.coroutines.launch
import java.lang.Error

@Composable
fun SheetContentSignUpScreen(
    viewModel: AuthViewModel,
    navigateToMainScreens: () -> Unit,
    navigateToLogInScreens: () -> Unit,
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.authState.collectAsState()
    val error = remember {
        mutableStateOf(viewModel.error.value)
    }
    val isDone = remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }


LaunchedEffect(state.value.isError){
    if (state.value.isError){
        Toast.makeText(context,viewModel.error.value,Toast.LENGTH_SHORT).show()
    }
}


Box(modifier = Modifier.fillMaxSize()) {

    if (showDialog){
        CustomDialog(
            isDone = isDone.value,
            error = error.value,
            onDone = {
                if (isDone.value){
                    navigateToMainScreens()
                }else {
                    showDialog=false
                }

            },
            isLoading = state.value.isLoading
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(placeholder = "Email", text = state.value.nEmail){
            viewModel.updateNEmail(it)
        }
        CustomTextField(placeholder = "Password", text = state.value.nPassword){
            viewModel.updateNPassword(it)
        }
        CustomTextField(placeholder = "Confirm Password", text = state.value.nPassword){

        }
        ElevatedButton(
            onClick = {
                scope.launch {
                    viewModel.createAnAccount {
                        isDone.value = it
                    }
                    showDialog=true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = DarkCyan),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Sign Up")
        }
        Row {
            CustomIconButton(image = R.drawable.google)
            CustomIconButton(image = R.drawable.facebook_bottom)
        }
    }
}

}

@Composable
fun CustomDialog(
    isDone:Boolean,
    error: String,
    onDone: () -> Unit,
    isLoading:Boolean
) {
    if (isLoading){
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }else {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = {

            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =if(isDone) "your account was created" else "we can't create your account",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Image(
                    painter = if (isDone )painterResource(id = R.drawable.ic_task_completed) else painterResource(
                        id = R.drawable.google
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
}

@Preview
@Composable
fun DialogPrev() {
    CustomDialog(isDone = true, error = "", isLoading = false, onDone = {

    })
}

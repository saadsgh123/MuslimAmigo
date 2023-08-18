package com.marsoftwar.muslimamigo.ui.loginsignup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.ui.theme.DarkCyan
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SheetContentLogInScreen(
    viewModel: AuthViewModel,
    navigateToMainScreen:()->Unit
) {

    val state by viewModel.authState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
                        if (it){
                            navigateToMainScreen()
                        }else {
                            Toast.makeText(context,viewModel.error.value,Toast.LENGTH_SHORT).show()
                        }
                    }
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
            CustomIconButton(image = R.drawable.google)
            CustomIconButton(image = R.drawable.facebook_bottom)
        }
    }
}
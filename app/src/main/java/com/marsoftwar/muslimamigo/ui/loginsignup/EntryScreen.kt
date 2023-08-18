package com.marsoftwar.muslimamigo.ui.loginsignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.ui.theme.DarkCyan

@Composable
fun EntryScreen(navigateToSignUp:(String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logimage),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(.55f)
        )
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            color = DarkCyan,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(bottom = 48.dp)
        )
        ElevatedButton(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = DarkCyan),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
            Text(text = "Continue with Facebook")
        }
        OutlinedButton(
            onClick = { navigateToSignUp(Auth_ids.SIGN_IN_ID) },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkCyan),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 8.dp)
        ) {

            Text(text = "I’ll use my Email")
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = ""
            )
        }
        Row {
            Text(text = "Already have an Account ?  ")
            Text(
                text = "Log in here",
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    navigateToSignUp(Auth_ids.LOG_IN_ID)
                }
            )
        }
    }
}

object Auth_ids {
    const val SIGN_IN_ID = "signup"
    const val LOG_IN_ID = "login"
}

@Preview(showBackground = true)
@Composable
fun LogPreview() {
    EntryScreen(navigateToSignUp = {})
}
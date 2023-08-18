package com.marsoftwar.muslimamigo.ui.loginsignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.ui.theme.DarkCyan
import com.marsoftwar.muslimamigo.viewmodels.AuthUiState
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInSignInScreen(modifier: Modifier = Modifier,id:String,content: @Composable() ((AuthViewModel) -> Unit)?) {

    val config = LocalConfiguration.current
    val height = config.screenHeightDp

    val authViewModel = hiltViewModel<AuthViewModel>()

    BottomSheetScaffold(
        sheetContent = {
         content?.invoke(authViewModel)
        },
    content = {
        ScreenImage()
    },
    sheetContentColor = Color.White,
        sheetPeekHeight = height.dp * 0.7f
    )
}

@Composable
fun ScreenImage() {
    Image(
        painter = painterResource(id = R.drawable.logimage),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterPrev() {}
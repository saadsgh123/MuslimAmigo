package com.marsoftwar.muslimamigo.ui.loginsignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(image:Int) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = CircleShape
            )
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = ""
        )
    }
}
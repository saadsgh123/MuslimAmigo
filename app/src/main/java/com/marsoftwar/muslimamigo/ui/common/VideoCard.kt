package com.marsoftwar.muslimamigo.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marsoftwar.muslimamigo.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCard() {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(280.dp)
            .padding(8.dp)
            .shadow(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(0.6f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.compose),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(11.dp))
                )
                Icon(
                    imageVector = Icons.Outlined.PlayCircle,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.compose),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Traveling doua",
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(11.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoCardPrev() {
    VideoCard()
}
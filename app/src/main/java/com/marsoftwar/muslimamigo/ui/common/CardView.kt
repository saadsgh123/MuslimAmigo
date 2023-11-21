package com.marsoftwar.muslimamigo.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marsoftwar.muslimamigo.R
import com.marsoftwar.muslimamigo.data.local.room.Douae

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(douae: Douae) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp)
            .shadow(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier=Modifier.clip(RoundedCornerShape(11.dp))) {
            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(11.dp)),
                contentScale = ContentScale.Crop
            )
            Text(text = "${douae.title}",
                modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                    .clip(RoundedCornerShape(11.dp))
            )
        }
    }
}

@Preview()
@Composable
fun CardPRECV() {
    //CardView()
}
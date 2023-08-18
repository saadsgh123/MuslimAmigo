package com.marsoftwar.muslimamigo.ui.video

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.marsoftwar.muslimamigo.ui.common.VideoCard

@Composable
fun VideoScreen(paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues) {
        repeat(10){
            item {
                VideoCard()
            }
        }
    }
}
package com.marsoftwar.muslimamigo.ui.home


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marsoftwar.muslimamigo.ui.common.CardView


@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        content = {
            LazyVerticalGrid(contentPadding = it, columns = GridCells.Fixed(2)) {
                repeat(16){
                    item {
                        CardView()
                    }
                }
            }
        },
        topBar = {
            //CustomTopAppBar(size =180.dp)
        }
    )
}



@Preview
@Composable
fun home_preview() {
    HomeScreen(paddingValues = PaddingValues(10.dp))
}
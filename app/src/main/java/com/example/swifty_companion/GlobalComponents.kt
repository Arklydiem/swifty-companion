package com.example.swifty_companion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.background42),
        contentDescription = "Background image 42",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
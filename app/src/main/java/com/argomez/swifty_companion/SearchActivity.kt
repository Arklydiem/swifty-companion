package com.argomez.swifty_companion

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.argomez.swifty_companion.ui.theme.SwiftyCompanionTheme

class SearchActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftyCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Background()
                    Content(
                        context = this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun Content(context: Context, modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Menu(context, modifier, screenHeight, screenWidth);
            Spacer(modifier = Modifier
                .height((screenHeight / 10) * .2f)
            )
            Body(context, modifier, screenHeight, screenWidth);
        }
    }
}

@Composable
private fun Menu(context: Context, modifier: Modifier = Modifier,
                 screenHeight: Dp, screenWidth: Dp) {
    Box(
        modifier = Modifier
            .height((screenHeight / 10) * 1)
            .width((screenWidth / 10) * 9)
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically, // Center content vertically
//            horizontalArrangement = Arrangement.Center,
        ) {
            SearchBar()
        }
    }
}

@Composable
private fun Body(context: Context, modifier: Modifier = Modifier,
                 screenHeight: Dp, screenWidth: Dp) {
    Box(
        modifier = Modifier
            .height((screenHeight / 10) * 8f)
            .width((screenWidth / 10) * 9)
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
    }
}

@Composable
private fun SearchBar() {
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = {textState.value = it},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        modifier = Modifier
            .width(192.dp)
    )
}
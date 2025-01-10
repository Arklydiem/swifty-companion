package com.argomez.swifty_companion

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argomez.swifty_companion.ui.theme.SwiftyCompanionTheme

class SearchActivity : ComponentActivity() {
    private val userFoundState = mutableStateOf(true)
    private val firstSearchDone = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftyCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Background()
                    Content(
                        context = this,
                        modifier = Modifier.padding(innerPadding),
                        userFound = userFoundState.value,
                        firstSearchDone = firstSearchDone.value,
                    )
                }
            }
        }
    }

    fun search(context: Context, toSearch: String) {
        val userFound = false
        firstSearchDone.value = true
        userFoundState.value = userFound
        val duration = Toast.LENGTH_SHORT

        if (userFound) {
            val toast = Toast.makeText(context, "User found : $toSearch", duration)
            toast.show()
        }
    }

    fun disconnection(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }
}

@Composable
private fun Content(context: Context, modifier: Modifier = Modifier,
                    userFound: Boolean, firstSearchDone: Boolean) {
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
            Menu(context, modifier, screenHeight, screenWidth)
            Spacer(modifier = Modifier
                .height((screenHeight / 10) * .2f)
            )
            Body(context, modifier, screenHeight, screenWidth, userFound, firstSearchDone)
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
            verticalAlignment = Alignment.CenterVertically, // Centrer le contenu verticalement
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SearchBar(context)
            LogOut(context)
        }
    }
}

@Composable
private fun Body(
    context: Context,
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    screenWidth: Dp,
    userFound: Boolean,
    firstSearchDone: Boolean
) {
    Box(
        modifier = Modifier
            .height((screenHeight / 10) * 8f)
            .width((screenWidth / 10) * 9)
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        if (firstSearchDone) {
            if (!userFound) {
                Text(
                    text = "User not found",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp
                )
            }
            else {
                Text(
                    text = "User found",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp
                )
            }
        }

    }
}

@Composable
private fun SearchBar(context: Context) {
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        label = {
            Text("Search login here")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { (context as? SearchActivity)?.search(context, textState.value) }
        ),
        modifier = Modifier
            .width(192.dp)
    )
}

@Composable
private fun LogOut(context: Context) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_logout_24),
        contentDescription = "Log in",
        tint = Color.White,
        modifier = Modifier
            .width(36.dp)
            .height(36.dp)
            .clickable { (context as? SearchActivity)?.disconnection(context) },
    )
}

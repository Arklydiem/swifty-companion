package com.example.swifty_companion

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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
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
import com.example.swifty_companion.ui.theme.SwiftyCompanionTheme

class SearchActivity : ComponentActivity() {
    private val userFoundState = mutableStateOf(true)
    private val firstSearchDone = mutableStateOf(false)

    /**
     * Lifecycle method that sets up the content view and initializes states.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state.
     */
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

    /**
     * Searches for a user by their login.
     *
     * @param context The context of the activity.
     * @param toSearch The login to search for.
     */
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

    /**
     * Handles user disconnection by navigating back to the main activity.
     *
     * @param context The context of the activity.
     */
    fun disconnection(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }
}

/**
 * Displays the main content of the search screen.
 *
 * @param context The context of the activity.
 * @param modifier A modifier applied to the composable.
 * @param userFound A boolean indicating if the user was found.
 * @param firstSearchDone A boolean indicating if a search has been performed.
 */
@Composable
private fun Content(context: Context, modifier: Modifier = Modifier,
                    userFound: Boolean, firstSearchDone: Boolean) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Menu(context, screenHeight, screenWidth)
            Body(screenHeight, screenWidth, userFound, firstSearchDone)
        }
    }
}

/**
 * Displays the menu bar containing the search bar and logout button.
 *
 * @param context The context of the activity.
 * @param screenHeight The height of the screen in Dp.
 * @param screenWidth The width of the screen in Dp.
 */
@Composable
private fun Menu(context: Context, screenHeight: Dp, screenWidth: Dp) {
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SearchBar(context)
            LogOut(context)
        }
    }
}

/**
 * Displays the body section showing search results.
 *
 * @param screenHeight The height of the screen in Dp.
 * @param screenWidth The width of the screen in Dp.
 * @param userFound A boolean indicating if the user was found.
 * @param firstSearchDone A boolean indicating if a search has been performed.
 */
@Composable
private fun Body(screenHeight: Dp, screenWidth: Dp, userFound: Boolean, firstSearchDone: Boolean) {
    Box(
        modifier = Modifier
            .width((screenWidth / 10) * 9)
            .height((screenHeight / 10) * 8)
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
            } else {
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

/**
 * Displays a search bar allowing the user to search for a login.
 *
 * @param context The context of the activity.
 */
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

/**
 * Displays a logout button that allows the user to disconnect.
 *
 * @param context The context of the activity.
 */
@Composable
private fun LogOut(context: Context) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_logout_24),
        contentDescription = "Log out",
        tint = Color.White,
        modifier = Modifier
            .width(36.dp)
            .height(36.dp)
            .clickable { (context as? SearchActivity)?.disconnection(context) },
    )
}

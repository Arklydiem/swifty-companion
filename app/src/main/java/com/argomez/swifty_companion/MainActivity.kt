package com.argomez.swifty_companion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argomez.swifty_companion.ui.theme.SwiftyCompanionTheme

class MainActivity : ComponentActivity() {
    /**
     * Lifecycle method that sets up the main content view.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftyCompanionTheme(darkTheme = true) {
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

/**
 * Displays the main content of the app, including the logo, spacer, and login button.
 *
 * @param context The context of the activity used for navigation or displaying toasts.
 * @param modifier A modifier applied to the composable.
 */
@Composable
private fun Content(context: Context, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo(modifier)
            LoginButton(context)
        }
    }
}

/**
 * Displays the app's logo with specific dimensions and transparency.
 */
@Composable
private fun Logo(modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val divisor = 1.5f

    Image(
        painter = painterResource(id = R.drawable.logo42),
        contentDescription = "Logo image 42",
        modifier = modifier
            .width(screenWidth / divisor)
            .height(screenWidth / divisor)
            .alpha(0.85f),
    )
}

/**
 * Displays the login button, which triggers the `connection` function when clicked.
 *
 * @param context The context of the activity used for navigation or displaying toasts.
 */
@Composable
private fun LoginButton(context: Context) {
    Button(
        onClick = { connection(context) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(alpha = 0.5f),
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_login_24),
                contentDescription = "Log in",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp),
            )
            Text(
                text = "Log in with 42",
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.futura_cyrillic_light, FontWeight.Thin),
                    Font(R.font.futura_cyrillic_medium, FontWeight.Normal),
                    Font(R.font.futura_cyrillic_heavy, FontWeight.Bold)
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Handles the login process by navigating to the search activity or showing an error message.
 *
 * @param context The context of the activity used for navigation or displaying toasts.
 */
private fun connection(context: Context) {
    val connectionEstablished = true

    if (connectionEstablished) {
        val intent = Intent(context, SearchActivity::class.java)
        context.startActivity(intent)
    } else {
        val text = "Error during login"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
}

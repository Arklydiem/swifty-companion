package com.argomez.swifty_companion

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftyCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayBackground()
                    DisplayContent(
                        context = this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayBackground() {
    Image(
        painter = painterResource(id = R.drawable.background42),
        contentDescription = "Background image 42",
        modifier = Modifier.fillMaxSize(),
        contentScale = androidx.compose.ui.layout.ContentScale.Crop
    )
}

@Composable
fun DisplayContent(context: Context, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), // Make the Box take the entire screen
        contentAlignment = Alignment.Center // Align content in the center
    ) {
        Column(
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            Logo()
            ScreenSpacer(5f)
            LoginButton(context)
        }
    }
}

@Composable
fun ScreenSpacer(divisor: Float) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp;

    Spacer(modifier = Modifier.height(screenHeight / divisor))
}

@Composable
fun Logo() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp;
    val divisor = 1.5f

    Image(
        painter = painterResource(id = R.drawable.logo42),
        contentDescription = "Logo image 42",
        modifier = Modifier
            .width(screenWidth / divisor)
            .height(screenWidth / divisor)
            .alpha(0.85f),
    )
}

@Composable
fun LoginButton(context: Context) {
    Button(
        onClick = { test(context) },
        modifier = Modifier
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(alpha = 0.5f), // Background color
            contentColor = Color.White,  // Text/Icon color
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        ButtonContent()
    }
}

@Composable
fun ButtonContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically, // Center content vertically
        horizontalArrangement = Arrangement.Center // Center content horizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_login_24),
            contentDescription = "Log in",
            modifier = Modifier.width(36.dp).height(36.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
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

fun test(context: Context) {
    val text = "Hello toast!"
    val duration = Toast.LENGTH_SHORT

    val toast = Toast.makeText(context, text, duration) // in Activity
    toast.show()
}
package com.example.exam_android.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.exam_android.components.BackgroundWrapper
import com.example.exam_android.utils.UserConstants
import androidx.core.net.toUri

@Composable
fun ProfilScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundWrapper {

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = UserConstants.USER_AVATAR),
                    contentDescription = "Avatar utilisateur",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Email",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 1f
                        )
                    ),
                    color = Color.DarkGray,
                )
                Spacer(modifier = Modifier.height(32.dp))

                ClickableUrl()
            }
        }
    }
}

@Composable
fun ClickableUrl() {
    val context = LocalContext.current
    val url = UserConstants.USER_EMAIL

    Text(
        text = url,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    )
}

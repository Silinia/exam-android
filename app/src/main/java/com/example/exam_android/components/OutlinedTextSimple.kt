// components/OutlinedTextSimple.kt
package com.example.exam_android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedTextSimple(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Texte de contour (noir)
        Text(
            text = text,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                drawStyle = Stroke(
                    miter = 10f,
                    width = 4f,
                    join = StrokeJoin.Round
                )
            )
        )

        // Texte principal (blanc)
        Text(
            text = text,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}
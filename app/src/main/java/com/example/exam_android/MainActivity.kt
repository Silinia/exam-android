package com.example.exam_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exam_android.navigation.AppNavigation
import com.example.exam_android.ui.theme.ExamandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamandroidTheme {
                    AppNavigation()
            }
        }
    }
}

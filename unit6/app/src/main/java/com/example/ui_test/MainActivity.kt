package com.example.ui_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestButtonScreen()
        }
    }
}

@Composable
fun TestButtonScreen() {
    // Biến trạng thái cho văn bản nút
    val buttonText = remember { mutableStateOf("Click Me") }

    Column {
        Button(onClick = {
            buttonText.value = "Button Clicked"
        }) {
            Text(buttonText.value)
        }
    }
}

@Preview
@Composable
fun PreviewTestButtonScreen() {
    TestButtonScreen()
}

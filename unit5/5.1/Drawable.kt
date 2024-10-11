package com.example.drawable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drawable.ui.theme.DrawableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawableTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DrawableScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DrawableScreen(modifier: Modifier = Modifier) {
    DrawableImage(modifier = modifier)
}

@Composable
fun DrawableImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.my_image),  // Ensure you have an image named my_image in res/drawable
        contentDescription = "My Image",
        modifier = modifier.size(100.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun DrawablePreview() {
    DrawableTheme {
        DrawableScreen()
    }
}
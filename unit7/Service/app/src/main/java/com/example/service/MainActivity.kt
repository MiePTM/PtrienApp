package com.example.service

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }

    private fun startService() {
        val intent = Intent(this, ForegroundService::class.java)
        startService(intent)
    }

    private fun stopService() {
        val intent = Intent(this, ForegroundService::class.java)
        stopService(intent)
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { startService() }) {
                Text(text = "Start Service")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { stopService() }) {
                Text(text = "Stop Service")
            }
        }
    }
}

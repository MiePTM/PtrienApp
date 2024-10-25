package com.example.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineExampleScreen(myViewModel)
        }
    }
}

@Composable
fun CoroutineExampleScreen(viewModel: MyViewModel) {
    val result by viewModel.result.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = result)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.startTasks() }) {
            Text("Bắt đầu tác vụ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoroutineExampleScreen() {
    val viewModel: MyViewModel = viewModel()
    CoroutineExampleScreen(viewModel)
}

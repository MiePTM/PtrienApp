package com.example.internet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        var resultMessage by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                startNetworkWorker { result, error ->
                    resultMessage = result ?: ""
                    errorMessage = error ?: ""
                }
            }) {
                Text("Fetch Data")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = resultMessage)
            Text(text = errorMessage)
        }
    }

    private fun startNetworkWorker(callback: (String?, String?) -> Unit) {
        val workManager = WorkManager.getInstance(this)

        val workRequest = OneTimeWorkRequestBuilder<NetworkWorker>()
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this) { workInfo ->
            if (workInfo != null && workInfo.state.isFinished) {
                val outputData = workInfo.outputData
                val result = outputData.getString("result")
                val data = outputData.getString("data")
                val error = outputData.getString("error")

                if (result == "success") {
                    callback(data, null)
                } else {
                    callback(null, error)
                }
            }
        }
    }
}

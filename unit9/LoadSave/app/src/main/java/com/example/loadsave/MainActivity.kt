package com.example.loadsave

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.loadsave.ui.theme.LoadSaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadSaveTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UserFormScreen(this)
                }
            }
        }
    }
}

@Composable
fun UserFormScreen(context: Context) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(false) }
    var isFemale by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Name Input
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Age Input
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Gender Checkboxes
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Checkbox(
                checked = isMale,
                onCheckedChange = {
                    isMale = it
                    if (it) isFemale = false // Ensure only one is selected
                }
            )
            Text("Male")

            Checkbox(
                checked = isFemale,
                onCheckedChange = {
                    isFemale = it
                    if (it) isMale = false // Ensure only one is selected
                }
            )
            Text("Female")
        }

        // Save and Load Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                saveUserData(sharedPreferences, name, age, isMale)
            }) {
                Text("Save")
            }

            Button(onClick = {
                val userData = loadUserData(sharedPreferences)
                name = userData.name
                age = userData.age
                isMale = userData.isMale
                isFemale = !userData.isMale
            }) {
                Text("Load")
            }
        }
    }
}

fun saveUserData(sharedPreferences: SharedPreferences, name: String, age: String, isMale: Boolean) {
    with(sharedPreferences.edit()) {
        putString("name", name)
        putString("age", age)
        putBoolean("isMale", isMale)
        apply()
    }
}

fun loadUserData(sharedPreferences: SharedPreferences): UserData {
    val name = sharedPreferences.getString("name", "") ?: ""
    val age = sharedPreferences.getString("age", "") ?: ""
    val isMale = sharedPreferences.getBoolean("isMale", true)
    return UserData(name, age, isMale)
}

data class UserData(val name: String, val age: String, val isMale: Boolean)

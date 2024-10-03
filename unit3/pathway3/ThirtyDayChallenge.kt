package com.example.thirtyday

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirtyday.model.Challenge
import com.example.thirtyday.model.ChallengesData
import com.example.thirtyday.ui.theme.ThirtyDayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDayTheme {
                ChallengeApp()
            }
        }
    }
}

@Composable
fun ChallengeApp(modifier: Modifier = Modifier) {
    val challenges = ChallengesData.challenges
    Scaffold(
        topBar = { topBarChallenge() }
    ) {
        it ->
        LazyColumn(contentPadding = it) {
            items(challenges.size) {
                challengeIndex ->
                ChallengeItem(
                    challenges[challengeIndex],
                    modifier = Modifier
                        .padding(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarChallenge(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        },
        modifier = modifier
    )
}

@Composable
fun ChallengeItem(challenge: Challenge, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        var displayDesc by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .animateContentSize(animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessHigh
            ))
        ) {
            Text(
                text = challenge.day,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text  = stringResource(challenge.name),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Image(
                painter = painterResource(challenge.image),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(onClick = { displayDesc = !displayDesc })
            )
            if (displayDesc) {
                Text(
                    text = stringResource(challenge.description)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    ThirtyDayTheme {
        ChallengeApp()
    }
}
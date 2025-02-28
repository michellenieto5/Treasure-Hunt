package com.example.mobiletreasurehunt.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height

@Composable
fun MobileTreasureHuntCompletedPage(
    totalElapsedTime: String,
    navController: NavHostController
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Congratulations!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You have completed the treasure hunt!", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total Time: $totalElapsedTime", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("start") }) {
            Text("Back to Start")
        }
    }
}



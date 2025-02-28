package com.example.mobiletreasurehunt.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mobiletreasurehunt.data.Clue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
@Composable
fun FoundCluePage(
    navController: NavHostController,
    clue: Clue,
    onContinue: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Clue Solved!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You have found the clue: ${clue.clueText}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Hint: ${clue.hint}", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onContinue() }) {
            Text("Continue")
        }
    }
}

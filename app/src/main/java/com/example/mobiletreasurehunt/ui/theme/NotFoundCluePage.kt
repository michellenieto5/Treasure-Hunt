package com.example.mobiletreasurehunt.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import com.example.mobiletreasurehunt.model.MobileTreasureHuntViewModel

@Composable
fun NotFoundCluePage(clueId: Int, navController: NavHostController, viewModel: MobileTreasureHuntViewModel) {
    val clue = viewModel.getClue(clueId)
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Clue Not Found", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You are not near the clue. Try again!", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            clue?.let {
                navController.navigate("clue/${clue.id}")
            }
        }) {
            Text("Back to Clue")
        }
    }
}


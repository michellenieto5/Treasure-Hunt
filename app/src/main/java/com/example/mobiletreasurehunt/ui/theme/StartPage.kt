package com.example.mobiletreasurehunt.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier


@Composable
fun StartPage(navController: NavHostController) {
    val rules = """
        Welcome to the Treasure Hunt!
        
        1. You will be given clues that lead you to various locations.
        2. Follow the clues to find the correct locations.
        3. Press the "Found It!" button when you believe you are at the correct location.
        4. The game ends when you solve all the clues.
        
        Have fun and good luck!
    """

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Treasure Hunt Game",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = rules,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("clue/1") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Game")
        }
    }
}



package com.example.mobiletreasurehunt.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mobiletreasurehunt.data.Clue
import com.example.mobiletreasurehunt.model.MobileTreasureHuntViewModel

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun CluePage(clue: Clue, viewModel: MobileTreasureHuntViewModel, navController: NavHostController) {
    val timerText = remember { mutableStateOf("00:00:00") }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            val elapsedTimeMillis = System.currentTimeMillis() - viewModel.startTime
            timerText.value = viewModel.formatElapsedTime(elapsedTimeMillis)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Elapsed Time: ${timerText.value}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = clue.clueText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.isCurrentLocationNearClue(clue, 700.0) { isNear ->
                    if (isNear) {
                        navController.navigate("found/${clue.id}")
                    } else {
                        navController.navigate("notFound/${clue.id}")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Found It!")
        }


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("hint/${clue.id}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hint")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("start")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Quit")
        }
    }
}

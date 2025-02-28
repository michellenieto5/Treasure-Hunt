package com.example.mobiletreasurehunt.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobiletreasurehunt.model.MobileTreasureHuntViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MobileTreasureHuntNavHost(navController: NavHostController, viewModel: MobileTreasureHuntViewModel) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartPage(navController) }
        composable("clue/{clueId}") { backStackEntry ->
            val clueId = backStackEntry.arguments?.getString("clueId")?.toIntOrNull() ?: 0
            val clue = viewModel.getClue(clueId)
            clue?.let { CluePage(clue, viewModel, navController) }
        }
        composable("hint/{clueId}") { backStackEntry ->
            val clueId = backStackEntry.arguments?.getString("clueId")?.toIntOrNull() ?: 0
            val clue = viewModel.getClue(clueId)
            clue?.let { HintPage(clue, navController) }
        }
        composable("found/{clueId}") { backStackEntry ->
            val clueId = backStackEntry.arguments?.getString("clueId")?.toIntOrNull() ?: 0
            val clue = viewModel.getClue(clueId)
            clue?.let {
                FoundCluePage(navController, clue) {
                    viewModel.moveToNextClue()
                    val nextClue = viewModel.getCurrentClue()
                    if (nextClue != null) {
                        navController.navigate("clue/${nextClue.id}") {
                            popUpTo("found/${clue.id}") { inclusive = true }
                        }
                    } else {
                        navController.navigate("completed") {
                            popUpTo("start") { inclusive = true }
                        }
                    }
                }
            }
        }
        composable("notFound/{clueId}") { backStackEntry ->
            val clueId = backStackEntry.arguments?.getString("clueId")?.toIntOrNull() ?: 0
            NotFoundCluePage(clueId, navController, viewModel)
        }
        composable("completed") {
            MobileTreasureHuntCompletedPage(totalElapsedTime = viewModel.elapsedTime, navController = navController)
        }
    }
}




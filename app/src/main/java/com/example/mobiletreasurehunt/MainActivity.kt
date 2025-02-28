package com.example.mobiletreasurehunt

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobiletreasurehunt.model.LocationLogic
import com.example.mobiletreasurehunt.model.MobileTreasureHuntViewModel
import com.example.mobiletreasurehunt.ui.theme.MobileTreasureHuntNavHost
import com.example.mobiletreasurehunt.ui.theme.MobileTreasureHuntTheme

class MainActivity : ComponentActivity() {

    private val locationLogic by lazy { LocationLogic(this) }
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var viewModel: MobileTreasureHuntViewModel

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                navigateToStartPage()
            }
        }

        checkLocationPermissions()

        setContent {
            MobileTreasureHuntTheme {
                viewModel = MobileTreasureHuntViewModel(locationLogic)
                val navController = rememberNavController()
                MobileTreasureHuntNavHost(navController, viewModel)
                LaunchedEffect(Unit) {
                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        navigateToStartPage(navController)
                    }
                }
            }
        }
    }

    private fun navigateToStartPage() {
    }

    private fun checkLocationPermissions() {
        when {
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun navigateToStartPage(navController: NavHostController) {
        navController.navigate("start")
    }
}

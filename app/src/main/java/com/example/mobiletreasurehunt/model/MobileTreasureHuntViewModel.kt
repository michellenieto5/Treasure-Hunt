package com.example.mobiletreasurehunt.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mobiletreasurehunt.data.Clue

class MobileTreasureHuntViewModel(private val locationLogic: LocationLogic) : ViewModel() {

    internal var currentClueIndex: Int = 0
    private val clues: List<Clue> = loadClues()

    val elapsedTime: String
        get() = formatElapsedTime(System.currentTimeMillis() - startTime)

    val startTime: Long = System.currentTimeMillis()

    fun getClue(id: Int): Clue? {
        return clues.find { it.id == id }
    }

    fun getCurrentClue(): Clue? {
        return if (currentClueIndex in clues.indices) clues[currentClueIndex] else null
    }

    fun moveToNextClue() {
        if (currentClueIndex < clues.size - 1) {
            currentClueIndex++
        }
    }

    fun isCurrentLocationNearClue(clue: Clue, threshold: Double, onResult: (Boolean) -> Unit) {
        Log.d("LocationCheck", "Checking if current location is near clue")

        locationLogic.getCurrentLocation { location ->
            location?.let {
                val currentGeo = Geo(it.latitude, it.longitude)
                val distance = currentGeo.haversine(clue.location)

                Log.d("LocationCheck", "Current location: Latitude = ${it.latitude}, Longitude = ${it.longitude}")
                Log.d("LocationCheck", "Clue location: Latitude = ${clue.location.lat}, Longitude = ${clue.location.lon}")
                Log.d("LocationCheck", "Distance to clue: $distance meters")

                onResult(distance <= threshold)
            } ?: run {
                Log.d("LocationCheck", "Current location is null")
                onResult(false)
            }
        }
    }

    private fun loadClues(): List<Clue> {
        return listOf(
            Clue(1, "In the heart of Old Town, where you can enjoy a refreshing treat, find the place where matcha meets ice cream. Seek the small shop that shares its address with a coffee shop. The sweet spot you seek is just north on Main Street.",
                Geo(37.6692, -122.3275),
                "The place you’re looking for is not far from where old memories and new flavors mix. Look for a spot known for its green ice cream."),
            Clue(
                2,
                "Find the most delicious hamburger in town, where you can enjoy 'animal style' food. This place is famous for its hats worn by employees, adding a fun twist to your meal. It's a fun fact about this restaurant you can't miss!",
                Geo(33.6636, -117.2985),
                "Look for the restaurant with a fun fact about its employees' hats and try the 'animal style' burger."
            )
        )
    }

    @SuppressLint("DefaultLocale")
    fun formatElapsedTime(milliseconds: Long): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
    }
}
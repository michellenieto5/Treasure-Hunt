package com.example.mobiletreasurehunt.data
import com.example.mobiletreasurehunt.model.Geo

data class Clue(
    val id: Int,
    val clueText: String,
    val location: Geo,
    val hint: String
)
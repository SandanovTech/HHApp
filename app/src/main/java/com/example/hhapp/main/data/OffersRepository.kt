package com.example.hhapp.main.data

import android.content.Context
import com.example.hhapp.main.model.ListOffersDTO
import kotlinx.serialization.json.Json

class OffersRepository(private val context: Context) {
    suspend fun loadOffersFromJsonFile(): ListOffersDTO {
        val offers = context.assets.open("offers.json").bufferedReader().use { it.readText() }
        return Json.decodeFromString<ListOffersDTO>(offers)
    }
}
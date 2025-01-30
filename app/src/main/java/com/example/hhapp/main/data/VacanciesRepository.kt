package com.example.hhapp.main.data

import android.content.Context
import com.example.hhapp.main.model.ListVacanciesDTO
import kotlinx.serialization.json.Json

class VacanciesRepository(private val context: Context) {
    suspend fun loadVacanciesFromJsonFile(): ListVacanciesDTO {
        val vacancies = context.assets.open("vacancies.json").bufferedReader().use { it.readText() }
        return Json.decodeFromString<ListVacanciesDTO>(vacancies)
    }
}
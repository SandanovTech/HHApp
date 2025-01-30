package com.example.hhapp.main.model

import kotlinx.serialization.Serializable

@Serializable
data class VacanciesDTO(
    val id: String? = null,
    val lookingNumber: Int? = null,
    val title: String? = null,
    val address: Address? = null,
    val company: String? = null,
    val experience: Experience? = null,
    val publishedDate: String? = null,
    var isFavorite: Boolean = false,
    val salary: Salary? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null,
)

@Serializable
data class ListVacanciesDTO(
    val vacancies: List<VacanciesDTO>
)

@Serializable
data class Address(
    val town: String? = null,
    val street: String? = null,
    val house: String? = null,
)
@Serializable
data class Experience(
    val previewText: String? = null,
    val text: String? = null,
)
@Serializable
data class Salary(
    val full: String? = null,
    val short: String? = null,
)
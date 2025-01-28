package com.example.hhapp.main.model

import kotlinx.serialization.Serializable

@Serializable
data class OffersDTO(
    val id: String? = null,
    val title: String? = null,
    val button: TextDTO? = null,
    val link: String? = null,
)

@Serializable
data class ListOffersDTO(
    val offers: List<OffersDTO>,
)

@Serializable
data class TextDTO(
    val text : String?
)

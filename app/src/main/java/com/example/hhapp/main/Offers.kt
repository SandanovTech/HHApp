package com.example.hhapp.main

enum class Offers {
    NEAR_VACANCIES,
    LEVEL_UP_RESUME,
    TEMPORARY_JOB;

    companion object {
        fun fromId(id : String?) : Offers? {
            return entries.find { it.name.equals(id, ignoreCase = true) }
        }
    }
}
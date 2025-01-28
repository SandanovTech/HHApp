package com.example.hhapp.main.di

import com.example.hhapp.main.data.OffersRepository
import com.example.hhapp.main.data.VacanciesRepository
import com.example.hhapp.main.viewmodels.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { OffersRepository(get()) }
    single { VacanciesRepository(get()) }
    viewModel { MainViewModel(get(), get()) }
}
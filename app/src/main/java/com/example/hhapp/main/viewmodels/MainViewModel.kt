package com.example.hhapp.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hhapp.main.data.OffersRepository
import com.example.hhapp.main.data.VacanciesRepository
import com.example.hhapp.main.model.ListOffersDTO
import com.example.hhapp.main.model.ListVacanciesDTO
import kotlinx.coroutines.launch

class MainViewModel(
    private val offersRepository: OffersRepository,
    private val vacanciesRepository: VacanciesRepository,
) : ViewModel() {
    private val _offers = MutableLiveData<ListOffersDTO>()
    val offers: LiveData<ListOffersDTO> = _offers
    private val _vacancies = MutableLiveData<ListVacanciesDTO>()
    val vacancies: LiveData<ListVacanciesDTO> = _vacancies

     fun loadOffers() {
        viewModelScope.launch {
            val listOffers = offersRepository.loadOffersFromJsonFile()
            _offers.value = listOffers
        }
    }
    fun loadVacancies() {
        viewModelScope.launch {
            val listVacancies = vacanciesRepository.loadVacanciesFromJsonFile()
            _vacancies.value = listVacancies
        }
    }
}
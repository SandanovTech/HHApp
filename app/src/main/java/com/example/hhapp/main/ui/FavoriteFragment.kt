package com.example.hhapp.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.R
import com.example.hhapp.databinding.FragmentFavoriteBinding
import com.example.hhapp.main.adapter.FavoriteVacanciesAdapter
import com.example.hhapp.main.model.ListVacanciesDTO
import com.example.hhapp.main.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteVacanciesAdapter
    private lateinit var recyclerViewFavoriteVacancies: RecyclerView
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadVacancies()
        viewModel.vacancies.value?.let {
            setupVacancies(it)
            val size = it.vacancies.count { it.isFavorite }
            binding.vacanciesCounterLabel.text =
                resources.getQuantityString(R.plurals.counter_vacancy, size, size)
        }
    }

    private fun setupVacancies(vacancies: ListVacanciesDTO) {
        recyclerViewFavoriteVacancies = binding.favoriteVacancies
        recyclerViewFavoriteVacancies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = FavoriteVacanciesAdapter(vacancies)
        recyclerViewFavoriteVacancies.adapter = adapter
    }
}
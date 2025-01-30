package com.example.hhapp.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.R
import com.example.hhapp.databinding.FragmentMoreVacanciesBinding
import com.example.hhapp.main.adapter.VacanciesAdapter
import com.example.hhapp.main.model.ListVacanciesDTO
import com.example.hhapp.main.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreVacanciesFragment : Fragment() {
    private var _binding: FragmentMoreVacanciesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterVacancies: VacanciesAdapter
    private lateinit var recyclerViewVacancies: RecyclerView
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoreVacanciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_main)
        viewModel.loadVacancies()
        viewModel.vacancies.value?.let {
            setupVacancies(it)
            val size =  it.vacancies.size
            binding.vacanciesLabel.text = resources.getQuantityString(R.plurals.more_n_vacancies,size, size)
        }
    }
    private fun setupVacancies(vacancies: ListVacanciesDTO) {
        recyclerViewVacancies = binding.vacancies
        recyclerViewVacancies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterVacancies = VacanciesAdapter(vacancies)
        recyclerViewVacancies.adapter = adapterVacancies
    }
}
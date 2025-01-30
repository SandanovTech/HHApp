package com.example.hhapp.main.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.R
import com.example.hhapp.databinding.FragmentMainBinding
import com.example.hhapp.main.adapter.OffersAdapter
import com.example.hhapp.main.adapter.VacanciesAdapter
import com.example.hhapp.main.model.ListOffersDTO
import com.example.hhapp.main.model.ListVacanciesDTO
import com.example.hhapp.main.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewOffers: RecyclerView
    private lateinit var recyclerViewVacancies: RecyclerView
    private lateinit var adapterOffers: OffersAdapter
    private lateinit var adapterVacancies: VacanciesAdapter
    private val viewModel: MainViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_main)
        binding.searchBar.textView.apply {
            this.ellipsize = TextUtils.TruncateAt.END
            this.isSingleLine = true
        }
        viewModel.loadOffers()
        viewModel.loadVacancies()
        viewModel.offers.value?.let {
            setupOffers(it)
        }
        viewModel.vacancies.value?.let {
            setupVacancies(it)
            val size = it.vacancies.size
            binding.continueBtn.text =
                resources.getQuantityString(R.plurals.more_n_vacancies, size, size)
        }
        binding.continueBtn.setOnClickListener {
            navController.navigate(R.id.moreVacanciesFragment)
        }
    }

    private fun setupOffers(offers: ListOffersDTO) {
        recyclerViewOffers = binding.offers
        recyclerViewOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterOffers = OffersAdapter(offers)
        recyclerViewOffers.adapter = adapterOffers
    }

    private fun setupVacancies(vacancies: ListVacanciesDTO) {
        recyclerViewVacancies = binding.vacancies
        recyclerViewVacancies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterVacancies = VacanciesAdapter(vacancies)
        recyclerViewVacancies.adapter = adapterVacancies
    }

}
package com.example.hhapp.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hhapp.R
import com.example.hhapp.databinding.FragmentVacancyDetailsBinding
import com.example.hhapp.main.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class VacancyDetailsFragment : Fragment() {
    private var _binding: FragmentVacancyDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentVacancyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: VacancyDetailsFragmentArgs by navArgs()
        val vacancyId = args.vacancyId
        viewModel.loadVacanciesById(vacancyId)
        binding.backArrowIcon.setOnClickListener {
            val action = VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToMainFragment()
            findNavController().navigate(action)
        }
        setupViews()
    }

    private fun setupViews() {
        viewModel.vacancyById.value?.let { vacancies ->
            for (vacancy in vacancies) {
                val addressParts = listOf(
                    vacancy.address?.town,
                    vacancy.address?.street,
                    vacancy.address?.house
                ).filterNotNull()
                val questions = vacancy.questions
                binding.respondBtn.setOnClickListener {
                    findNavController().navigate(VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToBottomSheetFragment(vacancy.id.toString()))
                }
                if (questions != null) {
                    for (question in questions) {
                        val chip = Chip(requireContext()).apply {
                            text = question
                            setChipBackgroundColorResource(R.color.gray2)
                        }
                        binding.chipGroup.addView(chip)
                    }
                }
                binding.vacancyLabel.text = vacancy.title
                binding.salaryLabel.text = vacancy.salary?.full
                binding.experienceLabel.text = vacancy.experience?.previewText
                binding.experienceDescription.text = vacancy.schedules?.iterator()?.next()
                binding.address.text = addressParts.joinToString(", ")
                binding.description.text = vacancy.description
                if (vacancy.appliedNumber == null) {
                    binding.respondLayout.visibility = View.INVISIBLE
                } else {
                    binding.respondLabel.text = requireContext().getString(
                        R.string.people_have_already_responded,
                        vacancy.appliedNumber
                    )
                }
                if (vacancy.lookingNumber == null) {
                    binding.lookingNowLayout.visibility = View.INVISIBLE
                } else {
                    binding.lookingNowLabel.text = requireContext().getString(
                        R.string.the_person_is_being_watched_right_now,
                        vacancy.lookingNumber
                    )
                }
                binding.responsibilities.text = vacancy.responsibilities
                binding.company.text = vacancy.company
            }
        }
    }

}
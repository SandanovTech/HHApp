package com.example.hhapp.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hhapp.R
import com.example.hhapp.databinding.FragmentBottomSheetBinding
import com.example.hhapp.main.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: VacancyDetailsFragmentArgs by navArgs()
        val vacancyId = args.vacancyId
        viewModel.loadVacanciesById(vacancyId)
        viewModel.vacancyById.value?.let {
            val vacancy = it.iterator().next()
            binding.titleJob.text = vacancy.title
        }
        binding.addLater.setOnClickListener {
            binding.addLater.visibility = View.INVISIBLE
            binding.addLaterInput.visibility = View.VISIBLE
        }
    }
}
package com.example.hhapp.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hhapp.R
import com.example.hhapp.databinding.EntryFragmentBinding

class EntryFragment : Fragment() {

    private var _binding: EntryFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = EntryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.continueBtn.setOnClickListener {
            val email = binding.emailTextField.text?.trim()
            if (email?.isNotEmpty() == true) {
                navController.navigate(R.id.action_EntryFragment_to_EntryCodeFragment)
            } else {
                Toast.makeText(view.context, "Fields is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
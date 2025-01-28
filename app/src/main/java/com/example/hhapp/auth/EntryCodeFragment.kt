package com.example.hhapp.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hhapp.R
import com.example.hhapp.databinding.EntryCodeFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EntryCodeFragment : Fragment() {

    private var _binding: EntryCodeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = EntryCodeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEntryCode()
        binding.continueBtn.setOnClickListener {
            checkAndNavigate()
        }
    }

    private fun setupEntryCode() {
        val firstCode = binding.codeEditText1
        val secondCode = binding.codeEditText2
        val threeCode = binding.codeEditText3
        val fourCode = binding.codeEditText4
        val editables = mutableListOf(firstCode, secondCode, threeCode, fourCode)

        for (index in editables.indices) {
            editables[index].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && index < editables.size - 1) {
                        // Переход к следующему полю
                        editables[index + 1].requestFocus()
                    }
//                    if (index == editables.size - 1) {
//                        checkAndNavigate()
//                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            editables[index].setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && editables[index].text.isNullOrEmpty() && index > 0) {
                    editables[index - 1].requestFocus()
                    true
                } else {
                    false
                }
            }
        }
    }
    private fun checkAndNavigate() {
        val code1 = binding.codeEditText1.text.toString()
        val code2 = binding.codeEditText2.text.toString()
        val code3 = binding.codeEditText3.text.toString()
        val code4 = binding.codeEditText4.text.toString()
        if (code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty() && code4.isNotEmpty()) {
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
            findNavController().navigate(R.id.action_entryCodeFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.codeEditText1.clearFocus()
        binding.codeEditText2.clearFocus()
        binding.codeEditText3.clearFocus()
        binding.codeEditText4.clearFocus()
        _binding = null
    }
}
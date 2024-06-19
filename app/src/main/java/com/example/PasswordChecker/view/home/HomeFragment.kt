package com.example.PasswordChecker.view.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.PasswordChecker.R
import com.example.PasswordChecker.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var continueButton: MaterialButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        passwordEditText = view.findViewById(R.id.password)
        continueButton = view.findViewById(R.id.button_continue)

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validatePassword(s.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })

        continueButton.setOnClickListener {
            if (continueButton.isEnabled) {
                findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
            }
        }
    }


    private fun validatePassword(password: String) {
        continueButton.isEnabled = false
        continueButton.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey))
        val isValid = password.length > 5 && password.any { it.isUpperCase() }
        continueButton.isEnabled = isValid

        val color = if (isValid) {
            ContextCompat.getColor(requireContext(), R.color.tealish)
        } else {
            ContextCompat.getColor(requireContext(), R.color.grey)
        }
        continueButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
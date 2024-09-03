package com.example.assignmentrxjava.presentation.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.assignmentrxjava.R
import com.example.assignmentrxjava.databinding.FragmentLoginBinding
import com.example.assignmentrxjava.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding

    private val minPasswordLength = 8
    private val maxPasswordLength = 15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.emailET.doOnTextChanged { _, _, _, _ ->
            viewModel.isEmailValid = Patterns.EMAIL_ADDRESS.matcher(binding.emailET.text).matches()
            binding.invalidEmail.visibility = if (!viewModel.isEmailValid) View.VISIBLE else View.INVISIBLE
            binding.loginBtn.isEnabled = viewModel.enableSubmitButton()
        }
        binding.passwordET.doOnTextChanged { _, _, _, _ ->
            viewModel.isPasswordValid = binding.passwordET.text.length in minPasswordLength..maxPasswordLength
            binding.invalidPass.visibility = if (!viewModel.isPasswordValid)  View.VISIBLE else View.INVISIBLE
            binding.loginBtn.isEnabled = viewModel.enableSubmitButton()
        }
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_postListFragment)
        }
    }

}
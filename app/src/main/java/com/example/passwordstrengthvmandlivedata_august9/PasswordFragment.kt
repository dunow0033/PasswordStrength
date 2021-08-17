package com.example.passwordstrengthvmandlivedata_august9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.passwordstrengthvmandlivedata_august9.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding: FragmentPasswordBinding get() = _binding!!

    private lateinit var viewModel: PasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)

        with(binding) {
            passwordEt.editText?.addTextChangedListener {
                viewModel.verifyPasswordStrength(it.toString())
            }

            viewModel.lowercasePresent.observe(viewLifecycleOwner, Observer {
                lowercaseTv.setTextColor(getColor(it))
            })

            viewModel.uppercasePresent.observe(viewLifecycleOwner, Observer {
                uppercaseTv.setTextColor(getColor(it))
            })

            viewModel.digitsPresent.observe(viewLifecycleOwner, Observer {
                digitsTv.setTextColor(getColor(it))
            })

            viewModel.meetLengthRequirement.observe(viewLifecycleOwner, Observer {
                lengthTv.setTextColor(getColor(it))
            })

            viewModel.enableButton.observe(viewLifecycleOwner, Observer {
                submitBtn.isEnabled = it
            })
        }
    }

    private fun getColor(isEnabled: Boolean): Int {
        val color = if (isEnabled) R.color.teal_200 else R.color.black
        return ContextCompat.getColor(requireContext(), color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
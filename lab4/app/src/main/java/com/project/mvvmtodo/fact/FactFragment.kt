package com.project.mvvmtodo.fact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.mvvmtodo.R
import com.project.mvvmtodo.databinding.FragmentFactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactFragment: Fragment(R.layout.fragment_fact) {

    private val viewModel: FactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFactBinding.bind(view)

        binding.apply {
            fabNewFact.setOnClickListener {
                viewModel.getFact()
            }
        }

        viewModel.responseFact.observe(viewLifecycleOwner) { fact ->
            binding.apply {
                textViewFact.text = fact.text
                textViewLink.text = fact.permalink
            }
        }
    }
}
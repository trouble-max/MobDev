package com.project.mvvmtodo.showterms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mvvmtodo.R
import com.project.mvvmtodo.databinding.FragmentTermsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment: Fragment(R.layout.fragment_terms) {

    private val viewModel: TermsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTermsBinding.bind(view)

        val termsAdapter = TermsAdapter()

        binding.apply {
            recyclerViewTerms.apply {
                adapter = termsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            fabNewTerms.setOnClickListener {
                viewModel.getTerms()
            }
        }

        viewModel.responseTerm.observe(viewLifecycleOwner) { terms ->
            termsAdapter.terms = terms
        }
    }
}
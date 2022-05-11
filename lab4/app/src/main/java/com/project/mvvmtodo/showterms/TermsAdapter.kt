package com.project.mvvmtodo.showterms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.mvvmtodo.api.Term
import com.project.mvvmtodo.databinding.FragmentTermBinding

class TermsAdapter: RecyclerView.Adapter<TermsAdapter.TermsViewHolder>() {

    inner class TermsViewHolder(val binding: FragmentTermBinding): RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Term>() {
        override fun areItemsTheSame(oldItem: Term, newItem: Term) =
            oldItem.term == newItem.term

        override fun areContentsTheSame(oldItem: Term, newItem: Term) =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var terms: List<Term>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        return TermsViewHolder(FragmentTermBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        val currentTerm = terms[position]

        holder.binding.apply {
            textViewTerm.text = currentTerm.term
            textViewDefinition.text = currentTerm.definition
        }
    }

    override fun getItemCount() = terms.size


}
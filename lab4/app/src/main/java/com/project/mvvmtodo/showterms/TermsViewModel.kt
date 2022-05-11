package com.project.mvvmtodo.showterms

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.mvvmtodo.api.Term
import com.project.mvvmtodo.api.TermRepository
import kotlinx.coroutines.launch

class TermsViewModel @ViewModelInject constructor(
    private val repository: TermRepository
): ViewModel() {

    private val _response = MutableLiveData<List<Term>>()
    val responseTerm : LiveData<List<Term>>
        get() = _response

    init {
        getTerms()
    }

    fun getTerms() = viewModelScope.launch {
        repository.getTerms().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("LOL", response.code().toString())
            }
        }
    }

}
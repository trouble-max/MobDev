package com.project.mvvmtodo.fact

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.mvvmtodo.api.Fact
import com.project.mvvmtodo.api.FactRepository
import kotlinx.coroutines.launch

class FactViewModel @ViewModelInject constructor(
    private val repository: FactRepository
): ViewModel() {

    private val _response = MutableLiveData<Fact>()
    val responseFact : LiveData<Fact>
        get() = _response

    init {
        getFact()
    }

    fun getFact() = viewModelScope.launch {
        repository.getFact().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("LOL", response.code().toString())
            }

        }
    }

}
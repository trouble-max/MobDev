package com.project.mvvmtodo.api

import javax.inject.Inject

class TermRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTerms() = apiService.getTerms()
}
package com.project.mvvmtodo.api

import javax.inject.Inject

class FactRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getFact() = apiService.getFact()
}
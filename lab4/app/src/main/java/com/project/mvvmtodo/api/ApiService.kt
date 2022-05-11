package com.project.mvvmtodo.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("bio/terms?num=2")
    suspend fun getTerms(): Response<List<Term>>

}
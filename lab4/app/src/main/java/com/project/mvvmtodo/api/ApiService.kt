package com.project.mvvmtodo.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/random.json?language=en")
    suspend fun getFact(): Response<Fact>

}
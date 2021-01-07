package com.koko.advice.data.network

import com.koko.advice.models.Result
import retrofit2.Response
import retrofit2.http.GET

interface AdviceApi {

    @GET("/advice")
     suspend fun getAdvice(): Response<Result>


}
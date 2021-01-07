package com.koko.advice.data

import com.koko.advice.data.network.AdviceApi
import com.koko.advice.models.Result
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val adviceApi: AdviceApi
) {

    suspend fun getAdviceResult(): Response<Result> {
        return adviceApi.getAdvice()
    }

}
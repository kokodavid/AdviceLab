package com.koko.advice.viewModels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koko.advice.data.Repository
import com.koko.advice.models.Result
import com.koko.advice.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application):
    AndroidViewModel(application) {
    var adviceResponse: MutableLiveData<NetworkResult<Result>> = MutableLiveData()
    fun getAdvices() = viewModelScope.launch {
        getAdviceSafeCall()
    }

    private suspend fun getAdviceSafeCall(){
        adviceResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.getAdviceResult()
            adviceResponse.value = handleAdviceResponse(response)


        } catch (e: Exception){
            adviceResponse.value = NetworkResult.Error("Advice not Found")
        }
    }
    private fun handleAdviceResponse(response: Response<Result>): NetworkResult<Result>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 ->{
                return NetworkResult.Error("API Key Limited.")
            }

            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }
    }







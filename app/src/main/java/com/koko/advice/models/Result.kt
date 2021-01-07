package com.koko.advice.models

import com.google.gson.annotations.SerializedName

data class Result(
        val slip: Slip
)

data class Slip(
        @SerializedName("advice")
        val advice: String,
        @SerializedName("slip_id")
        val slip_id: String
)


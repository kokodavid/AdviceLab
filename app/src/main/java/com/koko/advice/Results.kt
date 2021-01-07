package com.koko.advice

data class Results(
    val slip: Slip
)

data class Slip(
    val advice: String,
    val slip_id: String
)
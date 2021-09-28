package com.example.currency.data.network

import com.example.currency.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/v1/latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String = "868713694efeb65df90a8185b54fd2be"
    ): Response<CurrencyResponse>
}
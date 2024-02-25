package com.lucreziacarena.cryptoinsight.feature.home.data

import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CryptoApi {

    @GET("coins/markets")
    suspend fun getTopTenCrypto(@Query("vs_currency") currency:String, @Query("order") order:String, @Query("per_page") perPage:Int, @Query("page") pageNum:Int, @Query("sparkline") sparkline:Boolean): Response<CryptoList>
}

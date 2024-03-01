package com.lucreziacarena.cryptoinsight.network

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.CryptoDetailModel
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.history.CryptoHistory
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CryptoApi {

    @GET("coins/markets")
    suspend fun getTopTenCrypto(
        @Query("vs_currency") currency:String,
        @Query("order") order:String,
        @Query("per_page") perPage:Int,
        @Query("page") pageNum:Int, @Query("sparkline") sparkline:Boolean,
        @Query("x_cg_demo_api_key") apiKey: String
    ): CryptoList
    @GET("coins/{id}")
    suspend fun getCryptoDetail(@Path("id") coinId:String,  @Query("x_cg_demo_api_key") apiKey: String
    ): CryptoDetailModel
    @GET("coins/{id}/market_chart/range")
    suspend fun getCryptoHistory(
        @Path("id") coinId:String,
        @Query("vs_currency") currency:String,
        @Query("from") fromDate:String,
        @Query("to") toDate:String,
        @Query("precision") precision:String,
        @Query("x_cg_demo_api_key") apiKey: String

    ): CryptoHistory

}
